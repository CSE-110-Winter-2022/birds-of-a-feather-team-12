package com.example.team12bof;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.List;

public interface Sorter {

    List<Student> sort (AppDatabase db,List<Course> userCourses, String qtr, String year);
}
