package com.example.team12bof;

import com.example.team12bof.db.Course;

import java.util.List;

/**
 * This is the interface that we implement its methods
 * in other classes
 */
public abstract class IStudent {
    public abstract String getName();
    public abstract List<Course> getCourses();
    public abstract int getId();
}
