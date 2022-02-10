package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassmateActivity extends AppCompatActivity {
    protected RecyclerView classmatesRecyclerView;
    protected RecyclerView.LayoutManager classmatesLayoutManager;
    protected ClassmateViewAdapter classmateViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate);
        setTitle("List of BoFs");



        AppDatabase db = AppDatabase.singleton(getApplicationContext());




        List<? extends Student> classmates = db.studentDao().getAll();


        // TODO:
        classmatesRecyclerView = findViewById(R.id.classmates_view);

        classmatesLayoutManager = new LinearLayoutManager(this);
        classmatesRecyclerView.setLayoutManager(classmatesLayoutManager);

        classmateViewAdapter = new ClassmateViewAdapter(classmates);
        classmatesRecyclerView.setAdapter(classmateViewAdapter);

    }
}