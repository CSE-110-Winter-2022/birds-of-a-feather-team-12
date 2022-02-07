package com.example.team12bof.db;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

/**
 * This is the interface dao to use the database
 * from the StudentWithCoursesDao class
 */
@Dao
public interface StudentWithCoursesDao {
    @Transaction
    @Query("SELECT * FROM students")
    List<StudentWithCourses> getAll();

    @Query("SELECT * FROM students WHERE id=:id")
    StudentWithCourses get(int id);

    @Query("SELECT COUNT(*) from students")
    int count();
}
