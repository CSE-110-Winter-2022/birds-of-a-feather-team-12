package com.example.team12bof.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.team12bof.IStudent;

import java.util.Arrays;
import java.util.List;

// this is like "Person" in lab 5

/**
 * This class is to get students information like name and ID
 */
@Entity(tableName = "students")
public class Student {



    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int studentId=0;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="URL")
    public String URL;

    public Student(String name, String URL) {
        this.name = name;
        this.URL = URL;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }
}
