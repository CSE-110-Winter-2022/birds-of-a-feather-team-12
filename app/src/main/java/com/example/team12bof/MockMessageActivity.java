package com.example.team12bof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MockMessageActivity extends AppCompatActivity {

    private ArrayList<String> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_messages);
        messages = new ArrayList<>();
    }

    @Override
    protected void onPause() {
        super.onPause();
        messages.clear();

    }
    public void onDoneClicked(View view) {
        TextView messageView = findViewById(R.id.mockTextview);
        String message = messageView.getText().toString();
        messages.add(message);
        messageView.setText("");
    }

    public void onFinishClicked(View view) {
        Intent intent = new Intent(this, ClassmateActivity.class);
        intent.putExtra("messages", messages);
        startActivity(intent);
    }
}
