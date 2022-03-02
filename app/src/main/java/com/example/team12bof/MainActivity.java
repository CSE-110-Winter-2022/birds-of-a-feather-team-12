package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

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
        load();

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
       /* ProgressBar myBar = findViewById(R.id.progressBar);
        myBar.setVisibility(View.VISIBLE);

        Intent intent1 = new Intent(MainActivity.this, DemoService.class);
        startService(intent1);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, ClassmateActivity.class);
                startActivity(intent);
            }
        }, 2000);*/

        //super.onStart();
        Intent intent1 = new Intent(MainActivity.this, DemoService.class);
        startService(intent1);
        Intent intent = new Intent(MainActivity.this, ClassmateActivity.class);
        startActivity(intent);

    }


    public void load(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String name = preferences.getString("Name", "Sara");
        TextView nameView = findViewById(R.id.nameView);

        nameView.setText(name);

    }

    public void save(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        TextView nameView = findViewById(R.id.nameView);
        editor.putString("Name", nameView.getText().toString());
        editor.apply();
    }
    public void onEnterNameClicked(View view){
           save();

    }

    public void onMockClicked(View view) {
        Intent huh = new Intent(this, MockMessageActivity.class);
        startActivity(huh);
    }
}