package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

public class ClassmateActivity extends AppCompatActivity {
    protected RecyclerView classmatesRecyclerView;
    protected RecyclerView.LayoutManager classmatesLayoutManager;
    protected ClassmateViewAdapter classmateViewAdapter;

    private static final String TAG = "Lab5-nearby:";
    private MessageListener messageListener;

    private HashSet<String> alreadySeen;
    private HashSet<Course> UserownCoursesSet;
    private AppDatabase db;
    int sharedClasses =0;
    private Message myMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate);
        setTitle("List of BoFs");



        List<? extends Student> classmates = db.studentDao().getAll();

        Intent in = getIntent();
        String message = in.getStringExtra("message");

        alreadySeen = new HashSet<>();
        db = AppDatabase.singleton(this);

        List<Student> students = db.studentDao().getAll();

        List<Course> ownCourses = db.coursesDao().getForStudent(0);

        UserownCoursesSet = new HashSet<>();
        UserownCoursesSet.addAll(ownCourses);

        MessageListener realListener = new MessageListener() {
            @Override
            public void onFound(Message message) {
                Log.d(TAG, "Found message: " + new String(message.getContent()));

                String myMsg = message.getContent().toString();

                String[] spl = myMsg.split(",,,");
                String studentName = spl[0];
                String picUrl = spl[1];

                myMessage = new Message(studentName.getBytes(StandardCharsets.UTF_8));
                String[] courses = spl[2].split("\n");

                Course  newCourse;
                int studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
                for(int i = 0; i < courses.length; i++){
                    String[] splitedCourse = courses[i].split(",");
                    String dept = splitedCourse[2];
                    Log.d("Found new dept", dept);
                    String num = splitedCourse[3];
                    Log.d("Found new course num", num);
                    String year = splitedCourse[0];
                    Log.d("Found new year", year);
                    String qtr = splitedCourse[1];
                    Log.d("Found new qtr", qtr);
                    String size = splitedCourse[4];
                    Log.d("Found new size", size);

                    newCourse = new Course(studentId, num, dept,year, qtr,size);
                    if (UserownCoursesSet.contains(newCourse)) {
                        db.coursesDao().insert(newCourse);
                        sharedClasses++;
                    }
                    //Log.d(TAG, "num" + num);
                }

            }

            @Override
            public void onLost(Message message) {
                Log.d(TAG, "Lost sight of message: " + new String(message.getContent()));
            }
        };

        this.messageListener = new FakedMessageListener(realListener,message);


        // TODO:
        classmatesRecyclerView = findViewById(R.id.classmates_view);

        classmatesLayoutManager = new LinearLayoutManager(this);
        classmatesRecyclerView.setLayoutManager(classmatesLayoutManager);

        classmateViewAdapter = new ClassmateViewAdapter(classmates);
        classmatesRecyclerView.setAdapter(classmateViewAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        Nearby.getMessagesClient(this).subscribe(messageListener);
        Nearby.getMessagesClient(this).publish(myMessage);
    }

    @Override
    public void onStop() {
        Nearby.getMessagesClient(this).unsubscribe(messageListener);
        Nearby.getMessagesClient(this).unpublish(myMessage);
        super.onStop();
    }

    public void onStopClicked(View view){
        Intent myIntent = new Intent(this, DemoService.class);
        stopService(myIntent);
        Intent intent = new Intent(this,MainActivity.class );
        startActivity(intent);
    }
}