package com.example.team12bof;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.os.Bundle;
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

import com.example.team12bof.ClassmateDetailActivity;
import com.example.team12bof.R;
import com.example.team12bof.db.Course;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ClassmateDetailTest {
    @Rule
    public ActivityScenarioRule<ClassmateDetailActivity> scenarioRule = new ActivityScenarioRule<>(ClassmateDetailActivity.class);

    @Test
    public void testSameClass() {
        ActivityScenario<ClassmateDetailActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            //activity.onCreate(Bundle.EMPTY);
            assertTrue(activity.hasSameClass());
        });
    }

    /*
    @Test
    public void testHasSameClass() {
        ActivityScenario<ClassmateDetailActivity> scenario = scenarioRule.getScenario();

        // Make sure the activity is in the created state (so onCreated is called).
        scenario.moveToState(Lifecycle.State.CREATED);

        // When it's ready, we're ready to test inside this lambda (anonymous inline function).
        scenario.onActivity(activity -> {
            activity.SameClass = true;
            assertFalse(activity.hasSameClass());
        });
    }

     */
}