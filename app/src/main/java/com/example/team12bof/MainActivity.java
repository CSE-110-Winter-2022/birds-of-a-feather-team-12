package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
<<<<<<< Updated upstream
import android.widget.Button;

//Newonehgfhgfhgfhgfh
//rewugruywegruryewguygerwu
=======
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is the main activity class that has method
 * to do some activity on the button that is pushed
 */
>>>>>>> Stashed changes
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddClassClicked(View view) {
        Intent huh = new Intent(this, AddClassActivity.class);
        startActivity(huh);
    }

<<<<<<< Updated upstream
    public void onSearchClicked(View view){
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        startService(intent);
        Button start = (Button)findViewById(R.id.start);
        start.setVisibility(View.INVISIBLE);
        Button stop = (Button) findViewById(R.id.stop);
        stop.setVisibility(View.VISIBLE);
    }
=======
    public void onStartClicked(View view) {
        Intent intent = new Intent(MainActivity.this, DemoService.class);
       startService(intent);
        ProgressBar myBar = findViewById(R.id.progressBar);
        myBar.setVisibility(View.VISIBLE);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent myIntent = new Intent(MainActivity.this, ClassmateActivity.class);
               startActivity(myIntent);
            }
        }, 2000);




>>>>>>> Stashed changes

    public void onStopClicked(View view){
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        stopService(intent);
        Button stop = (Button) findViewById(R.id.stop);
        stop.setVisibility(View.INVISIBLE);
        Button start = (Button)findViewById(R.id.start);
        start.setVisibility(View.VISIBLE);

    }
}