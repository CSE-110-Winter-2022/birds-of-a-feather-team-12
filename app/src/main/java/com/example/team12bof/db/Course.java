package com.example.team12bof.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// this is like "Note" in lab 5

/**
 * This class is getting the all the information of the
 * intended course
 */
@Entity(tableName = "courses")
public class Course {

    /**
     * points to the ID
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int courseId;

    /**
     * Points to the student ID
     */
    @ColumnInfo(name = "student_id")
    public int studentId;

    //The course number
    @ColumnInfo(name = "course_number")
    public String course_number;

    // The course Feild
    @ColumnInfo(name = "subject")
    public String subject;

    // Year of the course
    @ColumnInfo(name = "year")
    public String year;

    // Quarter of the course
    @ColumnInfo(name = "quarter")
    public String quarter;


    /**
     * This is the constructor to initialize all the above variables
     * That we explained about them
     * @param courseId
     * @param studentId
     * @param course_number
     * @param subject
     * @param year
     * @param quarter
     */
    public Course(int courseId, int studentId, String course_number, String subject, String year, String quarter){
        this.courseId=courseId;
        this.studentId=studentId;
        this.course_number=course_number;
        this.subject=subject;
        this.year=year;
        this.quarter=quarter;

    }

    /**
     * This method will get the course ID
     * @return courseId
     */
    public int getCourseId(){
        return courseId;
    }
    /**
     * This method will get the student ID
     * @return studentId
     */
    public int getStudentId(){
        return studentId;
    }
    /**
     * This method will get the course number
     * @return course_number
     */
    public String getCourseNumber(){
        return course_number;
    }
    /**
     * This method will get the course subject
     * @return subject
     */
    public String getSubject(){
        return subject;
    }

    /**
     * This method will get the year that course has taken
     * @return year
     */
    public String getYear(){
        return year;
    }
    /**
     * This method will get the year that quarter has taken
     * @return quarter
     */
    public String getQuarter(){
        return quarter;
    }

}
