package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.team12bof.db.AppDatabase;

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
        List<? extends IStudent> classmates = db.studentsWithCoursesDao().getAll();

        List<DummyStudent> students = new ArrayList<DummyStudent>();
        students.add(new DummyStudent(0, "Jeannelle Balilo"));
        students.add(new DummyStudent(1,"Alejandro Lobo"));
        students.add(new DummyStudent(2,"Lingyu Chen"));
        students.add(new DummyStudent(3,"Ava Hamedi"));
        students.add(new DummyStudent(4,"Saman Zarei"));
        students.add(new DummyStudent(5,"Jinming Zhang"));

        // TODO:
        classmatesRecyclerView = findViewById(R.id.classmates_view);

        classmatesLayoutManager = new LinearLayoutManager(this);
        classmatesRecyclerView.setLayoutManager(classmatesLayoutManager);

        classmateViewAdapter = new ClassmateViewAdapter(students);
        classmatesRecyclerView.setAdapter(classmateViewAdapter);

    }

    public void onStopClicked(View view){

        Intent myIntent = new Intent (this, DemoService.class);
        stopService(myIntent);
        Intent intent = new Intent(this,MainActivity.class );
        startActivity(intent);

    }
}