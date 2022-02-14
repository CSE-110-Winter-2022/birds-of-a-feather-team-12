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

import java.util.ArrayList;
import java.util.List;

public class ClassmateDetailActivity extends AppCompatActivity {

    private RecyclerView coursesRecyclerView;
    private RecyclerView.LayoutManager coursesLayoutManager;
    private CoursesViewAdapter coursesViewAdapter;
    private RecyclerView classmatesRecyclerView;
    private RecyclerView.LayoutManager classmatesLayoutManager;

    public boolean SameClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate_detail);


        Intent intent = getIntent();
        int classmateId = intent.getIntExtra("classmate_id", 0);


        AppDatabase db = AppDatabase.singleton(this);
        Student classmate = db.studentDao().get(classmateId);
        List<Course> courses = db.coursesDao().getForStudent(classmateId);
        List<Course> user_courses= AddClassActivity.user_courses;
        List<Course>  shown_courses = new ArrayList<>();

        if(user_courses != null){

            for(int i=0;i<user_courses.size();i++){


                for(int j =0; j<courses.size();j++){

                    if(user_courses.get(i).getText().equals(courses.get(j).getText())){

                        shown_courses.add(user_courses.get(i));
                        SameClass = true;
                        break;
                    }
                }
            }
        }
        if(shown_courses.size()==0){
            Course course = new Course(0,"No Shared Courses", "","","");
            shown_courses.add(course);
            SameClass = false;
        }




        setTitle(classmate.getName());
        coursesViewAdapter = new CoursesViewAdapter(shown_courses);
        coursesRecyclerView = findViewById(R.id.courses_view);

        coursesLayoutManager = new LinearLayoutManager(this);
        coursesRecyclerView.setLayoutManager(coursesLayoutManager);



        coursesRecyclerView.setAdapter(coursesViewAdapter);
    }


    public void onGoBackClicked(View view) {
        finish();
    }

    public boolean hasSameClass() {
        return SameClass;
    }



}