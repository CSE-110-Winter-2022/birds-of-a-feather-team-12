package com.example.team12bof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.example.team12bof.db.Item;
import com.example.team12bof.db.Student;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.android.gms.nearby.Nearby;


public class ClassmateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppDatabase db;

    protected RecyclerView classmateRecyclerView;
    protected RecyclerView.LayoutManager classmateLayoutManager;
    protected ClassmateViewAdapter classmateViewAdapter;
    private HashSet<Course> UserCourses;
    private int startbutMode = 0;
    private MessageListener myMessageListener;
    private FakedMessageListener realListener;
    private static final String TAG = "team12bof";
    private HashSet<String> alreadySeen;
    private Spinner filter;
    private ArrayAdapter<CharSequence> adapter_filter;
    public static List<Student> listOfStudentsSession;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate);
        Log.d("onCreate", "called onCreate");

        Intent intent = getIntent();
        ArrayList<String> nearbyMessages = intent.getStringArrayListExtra("messages");
        filter = findViewById(R.id.filterSpin);
        adapter_filter = ArrayAdapter.createFromResource(this, R.array.filters, android.R.layout.simple_spinner_item);
        adapter_filter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(adapter_filter);
        filter.setOnItemSelectedListener(this);

        SharedPreferences preferences = getSharedPreferences("BOF", MODE_PRIVATE);
        boolean startmode = preferences.getBoolean("startmode", false);
        alreadySeen = new HashSet<>();

        db = AppDatabase.singleton(this);
        List<Student> classmates = db.studentDao().getAll();
        List<Course> myownCourses = db.coursesDao().getForStudent(0);
        UserCourses = new HashSet<>();
        UserCourses.addAll(myownCourses);

        classmateRecyclerView = findViewById(R.id.classmates_view);

        classmateLayoutManager = new LinearLayoutManager(this);
        classmateRecyclerView.setLayoutManager(classmateLayoutManager);




        classmateViewAdapter = new ClassmateViewAdapter(classmates);
        classmateRecyclerView.setAdapter(classmateViewAdapter);


        myMessageListener = new ClassmateActivity.MockedMessageListener();

        this.realListener = new FakedMessageListener(myMessageListener, nearbyMessages);


        if (startmode) {
            startbutMode = 0;
            findViewById(R.id.startbut).performClick();
            Log.d("Performed Click", "True");
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "Start");
        SharedPreferences preferences = getSharedPreferences("BOF", MODE_PRIVATE);
        boolean startmode = preferences.getBoolean("startmode", false);
        Log.d("startmode", "" + startmode);
        if (startmode == true) {
            startbutMode = 0;
            findViewById(R.id.startbut).performClick();
            Log.d("Performed Click", "True");
        }
    }

    public void onStartClicked(View view) {
        Log.d("onStartClicked", "clicked onStart");

       // TextView username = findViewById(R.id.nameView);
       // Message myMessage = new Message((username.getText().toString()).getBytes(StandardCharsets.UTF_8));


        Button startButton = findViewById(R.id.startbut);
        SharedPreferences preferences = getSharedPreferences("BOF", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (startbutMode == 0) {
            startbutMode = 1;
            startButton.setText("Stop");
            Nearby.getMessagesClient(this).subscribe(myMessageListener);
           // Nearby.getMessagesClient(this).publish(myMessage);
            editor.putBoolean("startmode", true);
            editor.apply();
            realListener.getMessage();
            listOfStudentsSession = new ArrayList<>();

        } else {
            listOfStudentsSession.clear();
            AlertDialog.Builder bd = new AlertDialog.Builder(this);
            bd.setMessage("Do you want to save this session?");
            bd.setPositiveButton("Yes", (dialog, id) -> {
                dialog.cancel();
                AlertDialog.Builder sbd = new AlertDialog.Builder(this);
                EditText input = new EditText(this);
                sbd.setTitle("Session name");
                sbd.setView(input);
                sbd.setMessage("Please enter the name of the session");
                sbd.setPositiveButton("Save", (dialog1, id1) -> {

                    Item s = new Item(input.getText().toString());
                    db.itemDao().insertItem(s);
                    dialog1.cancel();
                    for (Student sm : (db.studentDao().getAll())){
                        listOfStudentsSession.add(sm);
                    }


                });
                sbd.setCancelable(true);
                AlertDialog ad = sbd.create();
                ad.show();

            });
            bd.setNegativeButton("No",(dialog, id) ->{
                dialog.cancel();
            });
            bd.setCancelable(true);
            AlertDialog alertDialog = bd.create();
            alertDialog.show();
            startbutMode = 0;
            startButton.setText("Start");
            Nearby.getMessagesClient(this).unsubscribe(myMessageListener);
            //Nearby.getMessagesClient(this).unpublish(myMessage);
            editor.putBoolean("startmode", false);
            editor.apply();


        }
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d("oStop", "Stop");
        Nearby.getMessagesClient(this).unsubscribe(myMessageListener);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Sorter sorter;
        String selectedItem =  adapterView.getItemAtPosition(i).toString();
        if(AddClassActivity.user_courses == null) {
            List<Student> empty = new ArrayList<Student>();
            classmateViewAdapter = new ClassmateViewAdapter(empty);
            classmateRecyclerView.setAdapter(classmateViewAdapter);

        }
        else {
            if (selectedItem.equals("Prioritize recent")) {
                sorter = new SortRecent();
                List<Student> classmates = sorter.sort(db, AddClassActivity.user_courses, "Winter", "2022");
                classmateViewAdapter = new ClassmateViewAdapter(classmates);
                classmateRecyclerView.setAdapter(classmateViewAdapter);

            }
            if (selectedItem.equals("Prioritize small classes")) {
                sorter = new SortSmall();
                List<Student> classmates = sorter.sort(db, AddClassActivity.user_courses, "Winter", "2022");
                classmateViewAdapter = new ClassmateViewAdapter(classmates);
                classmateRecyclerView.setAdapter(classmateViewAdapter);
            }
            if (selectedItem.equals("This quarter only")) {
                sorter = new SortQuarterOnly();
                List<Student> classmates = sorter.sort(db, AddClassActivity.user_courses, "Winter", "2022");
                classmateViewAdapter = new ClassmateViewAdapter(classmates);
                classmateRecyclerView.setAdapter(classmateViewAdapter);

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class MockedMessageListener extends MessageListener {

        @Override
        public void onFound(@NonNull Message message) {
            String msg = new String(message.getContent());
            Log.d(TAG, "Found message: " + msg);


            if(!alreadySeen.contains(msg)){

                alreadySeen.add(msg);

                String studentName;
                String photoUrl;
                int shared = 0;
                Course newCourse;


                String[] data = msg.split(", ");
                studentName = data[0];
                photoUrl = data[1];


                String[] courses = data[2].split("/");

                Student newStudent = new Student(studentName);
                db.studentDao().insert(newStudent);
                int studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();

                for(int i =0 ; i< courses.length ; i++) {
                    String[] splitCourse = courses[i].split(" ");
                    String subject = splitCourse[0];
                    Log.d("Found new subject", subject);
                    String number = splitCourse[1];
                    Log.d("Found new course number", number);
                    String year = splitCourse[3];
                    Log.d("Found new year", year);
                    String quarter = splitCourse[2];
                    Log.d("Found new quarter", quarter);

                    String size = splitCourse[4];
                    Log.d("Found new size", size);

                    newCourse = new Course(studentId, number, subject, year, quarter, size);
                     Log.d(TAG, "number" + number);


                        db.coursesDao().insert(newCourse);
                        Log.d("Found new size", newCourse.text);
                        shared++;



                }


                List<Student> classmates = db.studentDao().getAll();
                Sorter sorter = new SortQuarterOnly();
                sorter.sort(db,AddClassActivity.user_courses,"Winter","2022");
                classmateViewAdapter = new ClassmateViewAdapter(classmates);
                classmateRecyclerView.setAdapter(classmateViewAdapter);
            }
        }

        @Override
        public void onLost(@NonNull Message message) {
            Log.d(TAG, "Lost sight of message: " + new String(message.getContent()));
        }
    }

    public void onFinishClicked(View view){
        Intent ins = new Intent(this,MainActivity.class);
        startActivity(ins);
    }

    public void onSavedClicked(View view){
        Intent in3 = new Intent(this, savedSessionActivity.class);
        startActivity(in3);
    }
}








