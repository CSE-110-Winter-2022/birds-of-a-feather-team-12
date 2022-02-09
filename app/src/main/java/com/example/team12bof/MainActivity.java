package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main activity class that has method
 * to do some activity on the button that is pushed
 */
public class MainActivity extends AppCompatActivity {
    /**
     * This method is used when an activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is designed to move to another activity(AddClassActivity)
     * When the Add Class button is pushed.
     * @param view
     */
    public void onAddClassClicked(View view) {
        Intent huh = new Intent(this, AddClassActivity.class);
        startActivity(huh);
    }

    public void onStartClicked(View view) {
        Intent intent = new Intent(this, ClassmateActivity.class);
        startActivity(intent);



    }
}