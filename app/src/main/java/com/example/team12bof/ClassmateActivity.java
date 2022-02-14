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

import java.util.ArrayList;
import java.util.Collections;
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

    public void onStopClicked(View view){
        Intent myIntent = new Intent(this, DemoService.class);
        stopService(myIntent);
        Intent intent = new Intent(this,MainActivity.class );
        startActivity(intent);
    }

    public boolean hasList() {
        AppDatabase db = AppDatabase.singleton(getApplicationContext());
        List<? extends Student> classmates = db.studentDao().getAll();
        if (!classmates.isEmpty()) { return true;}
        else { return false;}
    }
}