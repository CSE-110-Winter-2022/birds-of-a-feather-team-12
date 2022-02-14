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
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ClassmateActivityTest {
    @Rule
    public ActivityScenarioRule<ClassmateActivity> scenarioRule = new ActivityScenarioRule<>(ClassmateActivity.class);

    @Test
    public void testSingleton() {
        // Create a "scenario" to move through the activity lifecycle.
        // https://developer.android.com/guide/components/activities/activity-lifecycle
        ActivityScenario<ClassmateActivity> scenario = scenarioRule.getScenario();

        // Make sure the activity is in the created state (so onCreated is called).
        scenario.moveToState(Lifecycle.State.CREATED);

        // When it's ready, we're ready to test inside this lambda (anonymous inline function).
        scenario.onActivity(activity -> {
            RecyclerView list = activity.findViewById(R.id.classmates_view);
            assertTrue(activity.hasList());
        });
    }

    /*
    @Test
    public void testInput() {
        // This is an INTEGRATION test, as we're testing multiple units!
        // This test SHOULD fail. You need to fix it as an exercise!

        ActivityScenario<ClassmateActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {

        });
    }
     */

}
