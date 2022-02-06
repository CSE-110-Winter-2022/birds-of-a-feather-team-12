package com.example.team12bof;

import com.example.team12bof.db.Course;

import java.util.List;

public interface IStudent {
    int getId();
    String getName();
    List<Course> getCourses();
}
