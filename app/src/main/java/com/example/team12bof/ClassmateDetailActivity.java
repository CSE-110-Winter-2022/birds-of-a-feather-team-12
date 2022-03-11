package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
    private static int classmateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate_detail);


        Intent intent = getIntent();
        classmateId = intent.getIntExtra("classmate_id", 0);


        ToggleButton Tbutton = findViewById(R.id.toggleButton);

        String t = (ClassmateViewAdapter.myIds).toString();
        String[] mt = t.split(",");
        String com = Integer.toString(classmateId);
        for(int i =0; i< mt.length;i++){

            if( mt[i].equals(com)){
                Tbutton.setChecked(true);
            }
        }
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
            //Course course = new Course(0,"No Shared Courses", "","","","");
            //shown_courses.add(course);
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

        SharedPreferences preferences1 = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences1.edit();
        ToggleButton Tbutton = findViewById(R.id.toggleButton);


        String cl = Integer.toString(classmateId);
        if(Tbutton.isChecked()){
            ClassmateViewAdapter.myIds.append(cl).append(",");

        }
        else{
            String ghg = ( ClassmateViewAdapter.myIds).toString();
            String[] df = ghg.split(",");
            for(int k = 0; k < df.length; k++ ){
                if(df[k].equals(cl)){
                    df[k] = "-1";
                    ClassmateViewAdapter.myIds = (ClassmateViewAdapter.myIds).delete(0,(ClassmateViewAdapter.myIds).length());
                    for(int g = 0; g < df.length; g++){
                        ClassmateViewAdapter.myIds = (ClassmateViewAdapter.myIds).append(df[g]).append(",");
                    }
                }
            }

        }

        finish();
    }

    public boolean hasSameClass() {
        return SameClass;
    }

    public void onToggle(View view){
        ToggleButton Tbutton = findViewById(R.id.toggleButton);
        if(Tbutton.isChecked()){
            Toast.makeText( ClassmateDetailActivity.this,"Saved to Favorites", Toast.LENGTH_SHORT).show();
        }
    }



}