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
import android.widget.ToggleButton;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.team12bof.db.Course;

@RunWith(AndroidJUnit4.class)
public class FavoriteUnitTest {
    @Rule
    public ActivityScenarioRule<ClassmateDetailActivity> scenarioRule = new ActivityScenarioRule<>(ClassmateDetailActivity.class);

    @Test
    public void testToggled() {
        // Create a "scenario" to move through the activity lifecycle.
        // https://developer.android.com/guide/components/activities/activity-lifecycle
        ActivityScenario<ClassmateDetailActivity> scenario = scenarioRule.getScenario();

        // Make sure the activity is in the created state (so onCreated is called).
        scenario.moveToState(Lifecycle.State.CREATED);

        // When it's ready, we're ready to test inside this lambda (anonymous inline function).
        scenario.onActivity(activity -> {
            ToggleButton Tbutton = activity.findViewById(R.id.toggleButton);

            Tbutton.performClick();

            assertTrue(Tbutton.isChecked());
        });
    }

}
