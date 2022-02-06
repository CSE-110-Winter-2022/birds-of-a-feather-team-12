package com.example.team12bof.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// this is like "Note" in lab 5
@Entity(tableName = "courses")
public class Course {


    @PrimaryKey
    @ColumnInfo(name = "id")
    public int courseId;

    @ColumnInfo(name = "student_id")
    public int studentId;

    @ColumnInfo(name = "course_number")
    public String course_number;

    @ColumnInfo(name = "subject")
    public String subject;

    @ColumnInfo(name = "year")
    public String year;

    @ColumnInfo(name = "quarter")
    public String quarter;



    public Course(int courseId, int studentId, String course_number, String subject, String year, String quarter){
        this.courseId=courseId;
        this.studentId=studentId;
        this.course_number=course_number;
        this.subject=subject;
        this.year=year;
        this.quarter=quarter;

    }

    public int getCourseId(){
        return courseId;
    }
    public int getStudentId(){
        return studentId;
    }
    public String getCourseNumber(){
        return course_number;
    }
    public String getSubject(){
        return subject;
    }
    public String getYear(){
        return year;
    }
    public String getQuarter(){
        return quarter;
    }



}
