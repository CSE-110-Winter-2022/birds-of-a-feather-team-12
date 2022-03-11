package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.ArrayList;
import java.util.List;

public class sessionCourseActivity extends AppCompatActivity {

    private RecyclerView sesscoursesRecyclerView;
    private RecyclerView.LayoutManager sesscoursesLayoutManager;
    private sessionCourseViewAdapter sesscoursesViewAdapter;

    private static int myclassmateId;
    public boolean SameClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_course);

        Intent intent = getIntent();
        myclassmateId = intent.getIntExtra("classmate_id", 0);

        AppDatabase db = AppDatabase.singleton(this);
        Student myclassmate = db.studentDao().get(myclassmateId);
        List<Course> mycourses = db.coursesDao().getForStudent(myclassmateId);
        List<Course> user_courses= AddClassActivity.user_courses;
        List<Course>  shown_courses = new ArrayList<>();
        if(user_courses != null){

            for(int i=0;i<user_courses.size();i++){


                for(int j =0; j<mycourses.size();j++){

                    if(user_courses.get(i).getText().equals(mycourses.get(j).getText())){

                        shown_courses.add(user_courses.get(i));
                        SameClass = true;
                        break;
                    }
                }
            }
        }
        if(shown_courses.size()==0){
            //Course course = new Course(0,"No Shared Courses", "","","","");
            //shown_courses.add(course);
            SameClass = false;
        }

        setTitle(myclassmate.getName());
        sesscoursesViewAdapter = new sessionCourseViewAdapter(shown_courses);
        sesscoursesRecyclerView = findViewById(R.id.CoursesessionView);

        sesscoursesLayoutManager = new LinearLayoutManager(this);
        sesscoursesRecyclerView.setLayoutManager(sesscoursesLayoutManager);



        sesscoursesRecyclerView.setAdapter(sesscoursesViewAdapter);
    }
}