package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Item;
import com.example.team12bof.db.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class savedSessionActivity extends AppCompatActivity {

    protected RecyclerView sessionRecyclerView;
    protected RecyclerView.LayoutManager sessionLayoutManager;
    protected saveSessionViewAdapter sessionViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_session);
        setTitle("List");
        AppDatabase db = AppDatabase.singleton(getApplicationContext());
        List<? extends Item> items = db.itemDao().getAlllItems();

        sessionRecyclerView = findViewById(R.id.sessionView);

        sessionLayoutManager = new LinearLayoutManager(this);
        sessionRecyclerView.setLayoutManager(sessionLayoutManager);

        sessionViewAdapter = new saveSessionViewAdapter(items);
        sessionRecyclerView.setAdapter(sessionViewAdapter);
    }


}