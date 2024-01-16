package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;


public class StudentInfoActivity extends AppCompatActivity {
    private EditText name, age, college, enrollmentDate;
    private CheckBox computerNetwork, javaProgramming, embeddedSystems;
    private Spinner major;
    private RadioButton male, female;
    private Student student;
    private StuInfoOper stuInfoOper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        findAllViewsById();

        receiveData();
    }

    private void findAllViewsById() {
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        college = findViewById(R.id.college);
        enrollmentDate = findViewById(R.id.enrollmentDate);

        computerNetwork = findViewById(R.id.computerNetwork);
        javaProgramming = findViewById(R.id.javaProgramming);
        embeddedSystems = findViewById(R.id.embeddedSystems);

        major = findViewById(R.id.major);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
    }

    private void receiveData() {
        Intent receivedIntent = getIntent();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            Student student = receivedIntent.getSerializableExtra("student", Student.class);
            stuInfoOper = receivedIntent.getSerializableExtra("operationType", StuInfoOper.class);
        }
    }
}