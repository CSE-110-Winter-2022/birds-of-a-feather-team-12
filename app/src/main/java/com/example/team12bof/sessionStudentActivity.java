package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  sessionStudentActivity extends AppCompatActivity {
    protected RecyclerView soRecyclerView;
    protected RecyclerView.LayoutManager soLayoutManager;
    protected sessionStudentActivityViewAdapter soViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_student);
        setTitle("List of ");



        //AppDatabase db = AppDatabase.singleton(getApplicationContext());




        List<? extends Student> myclassmates = ClassmateActivity.listOfStudentsSession;


        // TODO:
        soRecyclerView = findViewById(R.id.sessionStudentView);

        soLayoutManager = new LinearLayoutManager(this);
        soRecyclerView.setLayoutManager(soLayoutManager);

        soViewAdapter = new sessionStudentActivityViewAdapter(myclassmates);
        soRecyclerView.setAdapter(soViewAdapter);



    }


}