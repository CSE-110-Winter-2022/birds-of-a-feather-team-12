package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Newonehgfhgfhgfhgfh
//rewugruywegruryewguygerwu
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

    public void onSearchClicked(View view){
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        startService(intent);
        Button start = (Button)findViewById(R.id.start);
        start.setVisibility(View.INVISIBLE);
        Button stop = (Button) findViewById(R.id.stop);
        stop.setVisibility(View.VISIBLE);
    }

    public void onStopClicked(View view){
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        stopService(intent);
        Button stop = (Button) findViewById(R.id.stop);
        stop.setVisibility(View.INVISIBLE);
        Button start = (Button)findViewById(R.id.start);
        start.setVisibility(View.VISIBLE);

    }
}