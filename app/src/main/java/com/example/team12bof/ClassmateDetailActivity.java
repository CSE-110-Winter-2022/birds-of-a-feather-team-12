package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.team12bof.db.AppDatabase;

public class ClassmateDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private IStudent classmate;

    private RecyclerView coursesRecyclerView;
    private RecyclerView.LayoutManager coursesLayoutManager;
    private CoursesViewAdapter coursesViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate_detail);

        Intent intent = getIntent();
        int classmateId = intent.getIntExtra("classmate_id", 0);

        db = AppDatabase.singleton(this);
        classmate = db.studentWithCoursesDao().getAll();

        String classmateName = intent.getStringExtra("classmate_name");
        String[] classmateCourses = intent.getStringArrayExtra("classmate_courses");

        setTitle(classmateName);
        classmateDetails.setText(String.join("\n", classmateCourses));
    }


    public void onGoBackClicked(View view) {
        finish();
    }
}