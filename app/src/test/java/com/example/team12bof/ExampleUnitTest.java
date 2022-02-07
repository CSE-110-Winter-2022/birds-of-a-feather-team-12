package com.example.team12bof;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.team12bof.db.Course;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testCourseNumber() {
        DummyStudent dummyStudent = new DummyStudent(0,"test");
        Course course = new Course(0,0,"110","CSE","2022","Winter");
        dummyStudent.addCourse(course);
        assertTrue("110".equals(dummyStudent.getCourses().get(0).getCourseNumber()));
    }
    @Test
    public void testYears() {
        DummyStudent dummyStudent = new DummyStudent(0,"test");
        Course course = new Course(0,0,"110","CSE","2022","Winter");
        dummyStudent.addCourse(course);
        assertEquals("2022",dummyStudent.getCourses().get(0).getYear());
    }
}