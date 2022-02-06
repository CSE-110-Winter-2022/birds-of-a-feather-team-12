package com.example.team12bof.db;

import androidx.room.Embedded;
import androidx.room.Embedded;
import androidx.room.Query;
import androidx.room.Relation;

import com.example.team12bof.IStudent;

import java.util.List;



public class StudentWithCourses implements IStudent {
    @Embedded
    public Student student;

    @Relation(parentColumn = "id",
            entityColumn = "student_id",
            entity = Course.class,
            projection = {"id"})
    public List<Course> courses;


    @Override
    public String getName() {
        return this.student.name;
    }
    @Override
    public List<Course> getCourses() {
        return this.courses;
    }
    @Override
    public int getId() {
        return this.student.studentId;
    }


}
