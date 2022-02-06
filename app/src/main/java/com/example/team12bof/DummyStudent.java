package com.example.team12bof;

import com.example.team12bof.db.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyStudent implements IStudent {
    private final int id;
    private final String name;
    private final List<Course> courses;

    public DummyStudent(int id, String name) {
        List courses = new ArrayList();
        this.id=id;
        this.name = name;
        this.courses = courses;
    }
    @Override
    public int getId() {return id;}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course){ courses.add(course); }
}
