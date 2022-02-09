package com.example.team12bof.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.team12bof.IStudent;

import java.util.List;


/**
 * This is the class to get the courses of the student and the student name
 */
public class StudentWithCourses extends IStudent {
    @Embedded
    public Student student;

    @Relation(parentColumn = "id",
            entityColumn = "student_id",
            entity = Course.class,
            projection = {"id"})
    public List<Course> courses;

    /**
     * This methid will get the student name
     * @return student.name
     */
    @Override
    public String getName() {
        return this.student.name;
    }

    /**
     * This methid will get the list of the courses
     * @return courses
     */
    @Override
    public List<Course> getCourses() {
        return this.courses;
    }
    /**
     * This methid will get the student id
     * @return student.studentId
     */
    public int getId() {
        return this.student.studentId;
    }


}
