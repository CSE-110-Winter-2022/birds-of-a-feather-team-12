package com.example.team12bof;



import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.team12bof.db.Course;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *:
 */

@RunWith(AndroidJUnit4.class)
public class AddNameUnitTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void testName() {
        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            EditText nameView = activity.findViewById(R.id.nameView);

            Button enterButton = activity.findViewById(R.id.button3);


            nameView.setText("JinmingZhang");

            enterButton.performClick();

            assertEquals("JinmingZhang", nameView.getText().toString());
//            assertNotNull("CSE", subjectView);
//            assertNotNull("2020", yearView);
//            assertNotNull("Winter", quarterView);
        });
    }
//    @Test
//    public void testYears() {
//        DummyStudent dummyStudent = new DummyStudent(0,"test");
//        Course course = new Course(0,"110","CSE","2022","Winter");
//        dummyStudent.addCourse(course);
//        assertEquals("2022",dummyStudent.getCourses().get(0).getYear());
//    }
}
