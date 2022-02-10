package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.List;

public class ClassmateDetailActivity extends AppCompatActivity {

    private RecyclerView coursesRecyclerView;
    private RecyclerView.LayoutManager coursesLayoutManager;
    private CoursesViewAdapter coursesViewAdapter;
    private RecyclerView classmatesRecyclerView;
    private RecyclerView.LayoutManager classmatesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate_detail);


        Intent intent = getIntent();
        int classmateId = intent.getIntExtra("classmate_id", 0);


        AppDatabase db = AppDatabase.singleton(this);
        Student classmate = db.studentDao().get(classmateId);
        List<Course> courses = db.coursesDao().getForStudent(classmateId);

        List<Course> courses_after_insertion = db.coursesDao().getForStudent(classmateId);


        setTitle(classmate.getName());
        coursesViewAdapter = new CoursesViewAdapter(courses_after_insertion);
        coursesRecyclerView = findViewById(R.id.courses_view);

        coursesLayoutManager = new LinearLayoutManager(this);
        coursesRecyclerView.setLayoutManager(coursesLayoutManager);



        coursesRecyclerView.setAdapter(coursesViewAdapter);
    }


    public void onGoBackClicked(View view) {
        finish();
    }
}