package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.team12bof.db.AppDatabase;

import java.util.List;

public class ClassmateDetailActivity extends AppCompatActivity {

    private RecyclerView coursesRecyclerView;
    private RecyclerView.LayoutManager coursesLayoutManager;
    private CoursesViewAdapter coursesViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate_detail);

        TextView classmateDetails = findViewById(R.id.courses_view);

        Intent intent = getIntent();
        int classmateId = intent.getIntExtra("classmate_id", 0);

        AppDatabase db = AppDatabase.singleton(this);
        IStudent classmate = db.studentsWithCoursesDao().get(classmateId);

        setTitle(classmate.getName());

        classmateDetails.setText(String.join("\n", classmate.getCourses().toArray(new String[0])));
    }


    public void onGoBackClicked(View view) {
        finish();
    }
}