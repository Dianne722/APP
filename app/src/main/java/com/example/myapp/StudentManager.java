package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentManager extends AppCompatActivity {

    private boolean isVisible = false;

    private ListView studentListView;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisible = true;
        if (!isVisible) {
            return;
        }

        studentListView = findViewById(R.id.studentList);

        studentList = new ArrayList<>();
        studentList.add("Student 1");
        studentList.add("Student 2");
        studentList.add("Student 3");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);

        studentListView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isVisible = false;
    }
}