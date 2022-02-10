package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

        Intent intent1 = new Intent(MainActivity.this, DemoService.class);
        startService(intent1);
        ProgressBar myBar = findViewById(R.id.progressBar);
        myBar.setVisibility(View.VISIBLE);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, ClassmateActivity.class);
                startActivity(intent);
            }
        }, 2000);





    }
}