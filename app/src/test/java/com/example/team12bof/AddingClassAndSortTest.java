package com.example.team12bof;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
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
public class AddingClassAndSortTest {

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

    @Rule
    public ActivityScenarioRule<AddClassActivity> scenarioRule = new ActivityScenarioRule<>(AddClassActivity.class);

//    @Test
//    public void testNoInput() {
//        // Create a "scenario" to move through the activity lifecycle.
//        // https://developer.android.com/guide/components/activities/activity-lifecycle
//        ActivityScenario<AddClassActivity> scenario = scenarioRule.getScenario();
//
//        // Make sure the activity is in the created state (so onCreated is called).
//        scenario.moveToState(Lifecycle.State.CREATED);
//
//        // When it's ready, we're ready to test inside this lambda (anonymous inline function).
//        scenario.onActivity(activity -> {
//            // No input have been run yet, so there shouldn't be an input!
//            assertFalse(activity.hasNum());
//            assertFalse(activity.hasSubject());
//        });
//    }


    @Test
    public void testInput() {
        // This is an INTEGRATION test, as we're testing multiple units!
        // This test SHOULD fail. You need to fix it as an exercise!

        ActivityScenario<AddClassActivity> scenario = scenarioRule.getScenario();
        Student testStudent1 = new Student("testStudent1","");
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        AppDatabase db = AppDatabase.singleton(context);

        db.studentDao().insert(testStudent1);



        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            EditText numView = activity.findViewById(R.id.number);
            EditText subjectView = activity.findViewById(R.id.subject);
            Button equalsButton = activity.findViewById(R.id.enter_btn);
            Spinner yearView = activity.findViewById(R.id.school_year);
            Spinner quarterView = activity.findViewById(R.id.quarter);
            Spinner classSizeView = activity.findViewById(R.id.class_size);

            numView.setText("110");
            subjectView.setText("CSE");
            yearView.setSelection(0);
            quarterView.setSelection(1);
            classSizeView.setSelection(3);
            equalsButton.performClick();

            assertEquals("110", numView.getText().toString());
            assertEquals("CSE", subjectView.getText().toString());
            assertEquals("2022", yearView.getSelectedItem().toString());
            assertEquals("Winter", quarterView.getSelectedItem().toString());
            assertEquals("Large(150-250)", classSizeView.getSelectedItem().toString());




            int studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
            Course testCourse = new Course(studentId, numView.getText().toString(),subjectView.getText().toString(),yearView.getSelectedItem().toString(),quarterView.getSelectedItem().toString(),classSizeView.getSelectedItem().toString());
            db.coursesDao().insert(testCourse);

            numView.setText("100");
            subjectView.setText("CSE");
            yearView.setSelection(1);
            quarterView.setSelection(0);
            classSizeView.setSelection(0);
            equalsButton.performClick();

            assertEquals("100", numView.getText().toString());
            assertEquals("CSE", subjectView.getText().toString());
            assertEquals("2021", yearView.getSelectedItem().toString());
            assertEquals("Fall", quarterView.getSelectedItem().toString());
            assertEquals("Tiny (less than 40)", classSizeView.getSelectedItem().toString());


            Student testStudent2 = new Student("testStudent2","");
            db.studentDao().insert(testStudent2);
            studentId = db.studentDao().getAll().get(db.studentDao().getAll().size()-1).getStudentId();
            Course testCourse1 = new Course(studentId, numView.getText().toString(),subjectView.getText().toString(),yearView.getSelectedItem().toString(),quarterView.getSelectedItem().toString(),classSizeView.getSelectedItem().toString());
            db.coursesDao().insert(testCourse1);
            Sorter sorter = new SortSmall();
            List<Student> sortedList = sorter.sort(db,userCourses,"Winter","2022");
            int sortedStudentCount = sortedList.size();

            assert(sortedList.get(0).getName().equals("testStudent2"));
            assert(sortedList.get(1).getName().equals("testStudent1"));
        });
    }

}
