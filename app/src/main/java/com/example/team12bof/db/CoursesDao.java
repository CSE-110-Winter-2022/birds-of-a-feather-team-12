package com.example.team12bof.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

/**
 * This is the interface dao to use the database
 * from the Course class
 */
@Dao
public interface CoursesDao {
    @Transaction
    @Query("SELECT * FROM courses where student_id=:studentId")
    List<Course> getForStudent(int studentId);

    @Query("SELECT * FROM courses where id=:id")
    Course get(int id);

    @Query("SELECT COUNT(*) FROM courses")
    int count();

    @Insert
    void insert(Course course);
    @Delete
    void delete(Course course);

}
