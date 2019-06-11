package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {
    private ArrayList<Schedule> scheduleArrayList;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Toolbar toolbar = findViewById(R.id.toolbar_schedule_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("VIT Freshers");

        scheduleArrayList = new ArrayList<>();

        // For Testing
        for (int i = 0; i < 10; i++) {
            scheduleArrayList.add(new Schedule());
        }

        listView = findViewById(R.id.schedule_listview);
        MyListAdapter myListAdapter = new MyListAdapter(this, scheduleArrayList);
        listView.setAdapter(myListAdapter);
    }
}
