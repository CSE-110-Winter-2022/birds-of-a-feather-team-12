package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.List;

public class sharedClassWith extends AppCompatActivity {


    private RecyclerView sharedClasses;
    private RecyclerView.LayoutManager sharedCoursesLayoutManager;
    private ClassmateViewAdapter sharedCoursesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_class_with);

        sharedClasses = (RecyclerView) findViewById(R.id.sharedClass_view);

        sharedCoursesAdapter = new ClassmateViewAdapter((List<? extends Student>) sharedClasses);

         sharedClasses = findViewById(R.id.sharedClass_view);

        sharedCoursesLayoutManager = new LinearLayoutManager(this);
        sharedClasses.setLayoutManager(sharedCoursesLayoutManager);



        sharedClasses.setAdapter(sharedCoursesAdapter);
    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}