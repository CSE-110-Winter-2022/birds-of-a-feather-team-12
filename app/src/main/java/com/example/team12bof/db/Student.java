package com.example.team12bof.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.team12bof.IStudent;

import java.util.Arrays;
import java.util.List;

// this is like "Person" in lab 5
@Entity(tableName = "students")
public class Student {



    @PrimaryKey
    @ColumnInfo(name = "id")
    public int studentId;

    @ColumnInfo(name="name")
    public String name;
}
