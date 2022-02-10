package com.example.team12bof;

import com.example.team12bof.db.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the class that determines the Courses that
 * the student has taken  before based on the course number and
 * field. And it adds them to the courseList
 */
public class DummyStudent extends IStudent {
    private final int id;
    private final String name;
    private final List<Course> courses;

    /**
     * This is the constructor that initializes
     * the course number, course field and list of
     * the courses
     * @param id
     * @param name
     */
    public DummyStudent(int id, String name) {
        List courses = new ArrayList();
        this.id=id;
        this.name = name;
        this.courses = courses;
    }

    /**
     * This is the method that return the course number
     * @return id
     */
    public int getId() {return id;}

    /**
     * This is the method that return the course field
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This is the method that return the course list
     * @return courses
     */
    @Override
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * This method will add the course in the list
     * @param course
     */
    public void addCourse(Course course){ courses.add(course); }
}
