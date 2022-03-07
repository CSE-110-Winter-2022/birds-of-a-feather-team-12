package com.example.team12bof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.CoursesDao;
import com.example.team12bof.db.Student;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;


public class ClassmateActivity extends AppCompatActivity {
    protected RecyclerView classmatesRecyclerView;
    protected RecyclerView.LayoutManager classmatesLayoutManager;
    protected ClassmateViewAdapter classmateViewAdapter;


    private MessageListener messageListener;


    protected RecyclerView personsRecyclerView;
    protected RecyclerView.LayoutManager personsLayoutManager;
    private AppDatabase db;

    protected RecyclerView studentRecyclerView;
    protected RecyclerView.LayoutManager studentLayoutManager;
    protected ClassmateViewAdapter studentViewAdapter;

    private MessageListener realListener;
    private FakedMessageListener testListener;

    private static final String TAG = "bofNearby";

    private int buttonState = 0;
    private HashSet<String> seenMessages;
    private HashSet<Course> ownCoursesSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate);
        Log.d("onCreate", "called onCreate");

        // Get list of messages from Nearby Messages Mock Screen
        Intent i = getIntent();
        ArrayList<String> messages = i.getStringArrayListExtra("messages");

        // Get current data stored in database
        db = AppDatabase.singleton(this);
        List<Student> students = db.studentDao().getAll();

        // Get user's own Courses
        List<Course> ownCourses = db.coursesDao().getForStudent(0);
        // reformat these courses into a hashset to make comparisons easier
        ownCoursesSet = new HashSet<>();
        ownCoursesSet.addAll(ownCourses);

        // Initialize a HashSet of messages that we've seen so far
        seenMessages = new HashSet<>();

        // Set up UI
        studentRecyclerView = findViewById(R.id.classmates_view);

        studentLayoutManager = new LinearLayoutManager(this);
        studentRecyclerView.setLayoutManager(studentLayoutManager);

        studentViewAdapter = new ClassmateViewAdapter(students);
        studentRecyclerView.setAdapter(studentViewAdapter);

        // Initialize our Message Listener
        realListener = new ClassmateActivity.builtInMessageListener();

        // Use Fake Message Listener for the demo
        this.testListener = new FakedMessageListener(realListener, messages);

        // Restarts search for new bof if it was never turned off by user
        SharedPreferences preferences = getSharedPreferences("BOF", MODE_PRIVATE);
        boolean isBofSearchOn = preferences.getBoolean("bofSearchOn", false);
        if (isBofSearchOn) {
            buttonState = 0;
            findViewById(R.id.runButton).performClick();
            Log.d("Performed Click", "True");
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "onStart called");
        SharedPreferences preferences = getSharedPreferences("BOF", MODE_PRIVATE);
        boolean isBofSearchOn = preferences.getBoolean("bofSearchOn", false);
        Log.d("isBofSearchOn", "" + isBofSearchOn);
        if (isBofSearchOn) {
            buttonState = 0;
            findViewById(R.id.runButton).performClick();
            Log.d("Performed Click", "True");
        }
    }

    public void onStartClicked(View view) {
        Log.d("onStartClicked", "clicked onStart");

        Button startButton = findViewById(R.id.runButton);

        // Build user message to publish to other students
        Message myMessage = new Message(buildMessage().getBytes(StandardCharsets.UTF_8));

        SharedPreferences preferences = getSharedPreferences("BOF", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Search is currently off
        if (buttonState == 0) {
            buttonState = 1;
            startButton.setText("Stop");
            Nearby.getMessagesClient(this).subscribe(realListener);
            Nearby.getMessagesClient(this).publish(myMessage);
            editor.putBoolean("bofSearchOn", true);
            editor.apply();
            testListener.getMessage();
        } else { // Search is currently on
            buttonState = 0;
            startButton.setText("Start");
            Nearby.getMessagesClient(this).unsubscribe(realListener);
            Nearby.getMessagesClient(this).unpublish(myMessage);
            editor.putBoolean("bofSearchOn", false);
            editor.apply();
        }
    }

    public String buildMessage() {

        SharedPreferences preferences = getSharedPreferences("BOF", MODE_PRIVATE);
        String name = preferences.getString("name", "");
        String photoURL = preferences.getString("image_url", "");

        List<Course> ownCourses = db.coursesDao().getForStudent(0);

        // Convert student data into desired format
        String message = "";
        message += name + ",,,\n";
        message += photoURL + ",,,\n";

        for (Course c : ownCourses) {
            message += c.getText() + "\n";
        }
        message.trim();

        Log.d("My message:", message);

        return message;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop", "onStop called");
        Nearby.getMessagesClient(this).unsubscribe(realListener);
    }

    // Our custom Message Listener
    public class builtInMessageListener extends MessageListener {

        @Override
        public void onFound(@NonNull Message message) {
            String rawString = new String(message.getContent());
            Log.d(TAG, "Found message: " + rawString);

            // Do not process repeated messages
            if(!seenMessages.contains(rawString)){
                // Add message to seen messages
                seenMessages.add(rawString);
                // Parse data from raw messages
                parseStudentMessage(rawString);
                // Refresh screen with new data after processing
                List<Student> students = db.studentDao().getAll();
                studentViewAdapter = new ClassmateViewAdapter(students);
                studentRecyclerView.setAdapter(studentViewAdapter);
            }
        }

        // Get student data from found message
        public void parseStudentMessage(String studentMessage){

            String studentName;
            String photoUrl;
            int numClassesOverlap = 0;
            CoursesDao courseDao = db.coursesDao();

            // Split message by the 3 commas
            String[] data = studentMessage.split(", ");
            studentName = data[0];
            photoUrl = data[1];

            // Split message by new line
            String[] courseParts = data[2].split(" ");

            Course newCourse;
            // Ensure that a new student ID is used
            Student newStudent = new Student(studentName);
            db.studentDao().insert(newStudent);
            int studentId = db.studentDao().getAll().size()-1;

            String dept = courseParts[0];
            Log.d("Found new dept", dept);
            String num = courseParts[1];
            Log.d("Found new course num", num);
            String year = courseParts[3];
            Log.d("Found new year", year);
            String qtr = courseParts[2];
            Log.d("Found new qtr", qtr);
            // TODO: Verify the correctness of class comparison?
                /*String size = courseParts[4];
                Log.d("Found new size", size);


            // Iterate through all course strings
            /*for(int i = 1; i < coursesString.length; i ++){
                String course = coursesString[i];
                String[] courseParts = course.split(" ");

                // Ensure that a new course ID is used

                String dept = courseParts[0];
                Log.d("Found new dept", dept);
                String num = courseParts[1];
                Log.d("Found new course num", num);
                String year = courseParts[3];
                Log.d("Found new year", year);
                String qtr = courseParts[2];
                Log.d("Found new qtr", qtr);
                // TODO: Verify the correctness of class comparison?
                /*String size = courseParts[4];
                Log.d("Found new size", size);

                 */

                // Create new course
                newCourse = new Course(5, num, dept, year, qtr);
                Log.d(TAG,"num" + num);


                // If new course matches with one of the user's courses, add it to the database

                    db.coursesDao().insert(newCourse);
                    Log.d("Found new size", newCourse.text);
                    numClassesOverlap++;


            // If new student has 1 or more shared courses, add them to the student database


        }

        @Override
        public void onLost(@NonNull Message message) {
            Log.d(TAG, "Lost sight of message: " + new String(message.getContent()));
        }
    }

}


/*
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_classmate);
        setTitle("List of BoFs");

        MessageListener realListener = new MessageListener() {
            @Override
            public void onFound(Message message) {
               String msg = new String((message.getContent()));
                Log.d(TAG, "Found message: " + msg);
                Toast.makeText(getApplication(), "Lost sight of message: " + msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLost(Message message) {
                Log.d(TAG, "Lost sight of message: " + new String(message.getContent()));
            }
        };



        onStart();


        classmatesRecyclerView = findViewById(R.id.classmates_view);

        classmatesLayoutManager = new LinearLayoutManager(this);
        classmatesRecyclerView.setLayoutManager(classmatesLayoutManager);





       /* AppDatabase db = AppDatabase.singleton(getApplicationContext());




        List<? extends Student> classmates = db.studentDao().getAll();


        // TODO:
        classmatesRecyclerView = findViewById(R.id.classmates_view);

        classmatesLayoutManager = new LinearLayoutManager(this);
        classmatesRecyclerView.setLayoutManager(classmatesLayoutManager);

        classmateViewAdapter = new ClassmateViewAdapter(classmates);
        classmatesRecyclerView.setAdapter(classmateViewAdapter);*/


/*
    public void onStopClicked(View view){
        onStop();
        Intent myIntent = new Intent(this, DemoService.class);
        stopService(myIntent);
        Intent intent = new Intent(this,MainActivity.class );
        startActivity(intent);
    }
    public void onStart() {
        super.onStart();
        Nearby.getMessagesClient(this).subscribe(messageListener);
    }

    @Override
    public void onStop() {
        Nearby.getMessagesClient(this).unsubscribe(messageListener);
        super.onStop();
    }
    */
