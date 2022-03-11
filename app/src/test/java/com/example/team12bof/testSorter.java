package com.example.team12bof;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.team12bof.SortQuarterOnly;
import com.example.team12bof.Sorter;
import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.CoursesDao;
import com.example.team12bof.db.Student;
import com.example.team12bof.db.StudentDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class testSorter {


    private AppDatabase db;
    private List<Course> userCourses;

    @Before
    public void createDb(){

        Course testCourse = new Course(10, "110","CSE","2022","Winter","Large(150-250)");
        Course testCourse1 = new Course(10, "100","CSE","2021","Fall","Tiny (less than 40)");
        userCourses = new ArrayList<Course>();
        userCourses.add(testCourse);
        userCourses.add(testCourse1);


    }

    @Test
    public void testThisQtrOnlySort(){
        Student testStudent1 = new Student("testStudent1");
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        AppDatabase db = AppDatabase.singleton(context);

        db.studentDao().insert(testStudent1);

        int studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
        Course testCourse = new Course(studentId, "110","CSE","2022","Winter","Large(150-250)");
        db.coursesDao().insert(testCourse);
        Student testStudent2 = new Student("testStudent2");
        db.studentDao().insert(testStudent2);
        studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
        Course testCourse1 = new Course(studentId, "100","CSE","2021","Fall","Tiny (less than 40)");
        db.coursesDao().insert(testCourse1);
        Sorter sorter = new SortQuarterOnly();
        List<Student> sortedList = sorter.sort(db,userCourses,"Winter","2022");
        int sortedStudentCount = sortedList.size();

        assert(sortedStudentCount == 1);






    }
    @Test
    public void testSmallSort(){
        Student testStudent1 = new Student("testStudent1");
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        AppDatabase db = AppDatabase.singleton(context);

        db.studentDao().insert(testStudent1);

        int studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
        Course testCourse = new Course(studentId, "110","CSE","2022","Winter","Large(150-250)");
        db.coursesDao().insert(testCourse);
        Student testStudent2 = new Student("testStudent2");
        db.studentDao().insert(testStudent2);
        studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
        Course testCourse1 = new Course(studentId, "100","CSE","2021","Fall","Tiny (less than 40)");
        db.coursesDao().insert(testCourse1);
        Sorter sorter = new SortSmall();
        List<Student> sortedList = sorter.sort(db,userCourses,"Winter","2022");
        int sortedStudentCount = sortedList.size();

        assert(sortedList.get(0).getName().equals("testStudent2"));
        assert(sortedList.get(1).getName().equals("testStudent1"));






    }
    @Test
    public void testRecentSort(){
        Student testStudent1 = new Student("testStudent1");
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        AppDatabase db = AppDatabase.singleton(context);

        db.studentDao().insert(testStudent1);

        int studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
        Course testCourse = new Course(studentId, "110","CSE","2022","Winter","Large(150-250)");
        db.coursesDao().insert(testCourse);
        Student testStudent2 = new Student("testStudent2");
        db.studentDao().insert(testStudent2);
        studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
        Course testCourse1 = new Course(studentId, "100","CSE","2021","Fall","Tiny (less than 40)");
        db.coursesDao().insert(testCourse1);
        Sorter sorter = new SortRecent();
        List<Student> sortedList = sorter.sort(db,userCourses,"Winter","2022");
        int sortedStudentCount = sortedList.size();

        assert(sortedList.get(0).getName().equals("testStudent1"));
        assert(sortedList.get(1).getName().equals("testStudent2"));






    }
}
