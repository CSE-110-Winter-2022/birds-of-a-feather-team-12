package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MockScreenActivity extends AppCompatActivity {

    private StringBuilder textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_screen);
        textMessage = new StringBuilder();
        TextView mytext = findViewById(R.id.mockText);

        textMessage.append(mytext.getText().toString());
        mytext.setText("");
        Intent intent = new Intent(this, ClassmateActivity.class);
        intent.putExtra("message", textMessage.toString());
        startActivity(intent);
    }






    public void onDoneClicked(View view){

    }
}