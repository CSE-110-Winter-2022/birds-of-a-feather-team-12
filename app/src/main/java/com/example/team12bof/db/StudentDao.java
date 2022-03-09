package com.example.team12bof.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM students")
    List<Student> getAll();

    @Query("SELECT * FROM students WHERE id=:id")
    Student get(int id);

    @Insert
    void insert(Student Student);

    @Delete
    void delete(Student Student);
}
