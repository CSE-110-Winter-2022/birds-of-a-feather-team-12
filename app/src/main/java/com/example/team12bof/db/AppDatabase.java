package com.example.team12bof.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This is an abstract class and has method to
 * write to data base or read from it by getting
 * inherited from RoomDatabase
 */
@Database(entities = {Student.class, Course.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase singletonInstance;

    /**
     * This method is checking if there is any database if in the file
     * and if it is, it return that databased
     * @param context
     * @return singletonInstance
     */
    public static AppDatabase singleton(Context context) {
        if (singletonInstance ==null) {
            singletonInstance = Room.databaseBuilder(context, AppDatabase.class, "students.db")
                    .createFromAsset("starter-students.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return singletonInstance;
    }
    public static void useTestSingleton(Context context) {
        singletonInstance = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        //return singletonInstance;
    }

    /**
     * This method will return a Dao test for StrudentWithCourses class
     * (A course for the student dao to check it)
     * @return StudentWithCoursesDao
     */
    public abstract StudentDao studentDao();

    /**
     * This method will return a Dao test for the Course class
     * to check it if it works correctly
     * @return CoursesDao
     */
    public abstract CoursesDao coursesDao();


}