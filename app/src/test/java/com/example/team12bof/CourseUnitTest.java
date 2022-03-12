package com.example.team12bof;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;

import com.example.team12bof.db.Course;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *:
 */

@RunWith(JUnit4.class)
public class CourseUnitTest {

    @Test
    public void testCourseNumber() {
        DummyStudent dummyStudent = new DummyStudent(0,"test");
        Course course = new Course(0,"110","CSE","2022","Winter", "Tiny (less than 40)");
        dummyStudent.addCourse(course);
        assertTrue("110".equals(dummyStudent.getCourses().get(0).getCourseNumber()));
    }
    @Test
    public void testYears() {
        DummyStudent dummyStudent = new DummyStudent(0,"test");
        Course course = new Course(0,"110","CSE","2022","Winter","Tiny (less than 40)");
        dummyStudent.addCourse(course);
        assertEquals("2022",dummyStudent.getCourses().get(0).getYear());
    }
}