package com.example.team12bof;

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

@RunWith(AndroidJUnit4.class)
public class AddClassUnitTest {
    @Rule
    public ActivityScenarioRule<AddClassActivity> scenarioRule = new ActivityScenarioRule<>(AddClassActivity.class);

    @Test
    public void testNoInput() {
        // Create a "scenario" to move through the activity lifecycle.
        // https://developer.android.com/guide/components/activities/activity-lifecycle
        ActivityScenario<AddClassActivity> scenario = scenarioRule.getScenario();

        // Make sure the activity is in the created state (so onCreated is called).
        scenario.moveToState(Lifecycle.State.CREATED);

        // When it's ready, we're ready to test inside this lambda (anonymous inline function).
        scenario.onActivity(activity -> {
            // No input have been run yet, so there shouldn't be an input!
            assertFalse(activity.hasNum());
            assertFalse(activity.hasSubject());
        });
    }


    @Test
    public void testInput() {
        // This is an INTEGRATION test, as we're testing multiple units!
        // This test SHOULD fail. You need to fix it as an exercise!

        ActivityScenario<AddClassActivity> scenario = scenarioRule.getScenario();

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
            classSizeView.setSelection(2);
            equalsButton.performClick();

            assertEquals("110", numView.getText().toString());
            assertEquals("CSE", subjectView.getText().toString());
            assertEquals("2022", yearView.getSelectedItem().toString());
            assertEquals("Winter", quarterView.getSelectedItem().toString());
            assertEquals("Medium(75-150)", classSizeView.getSelectedItem().toString());
        });
    }

}
