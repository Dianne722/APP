package com.example.myapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;


public class StudentInfoActivity extends AppCompatActivity {
    private EditText name, age, college, enrollmentDate;
    private CheckBox computerNetwork, javaProgramming, embeddedSystems;
    private Spinner major;
    private RadioButton male, female;
    private Student student;
    private StuInfoOper stuInfoOper;
    private Button submitButton;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        findAllViewsById();

        receiveData();

        bindSubmitBtn();
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

        submitButton = findViewById(R.id.submit);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void receiveData() {
        Intent receivedIntent = getIntent();
        Student student = receivedIntent.getSerializableExtra("student", Student.class);
        stuInfoOper = receivedIntent.getSerializableExtra("operationType", StuInfoOper.class);

        showInfo();
    }

    private void showInfo() {
        name.setText(student.getName());
        age.setText(student.getAge());
        if (student.getCollegeId() == 1) {
            college.setText("信息科学与工程学院");
        } else if (student.getCollegeId() == 2) {
            college.setText("土木学院");
        } else if (student.getCollegeId() == 3) {
            college.setText("机电学院");
        }

        enrollmentDate.setText(student.getEnrollmentDate().toString());

        showSelectedCourses(student.getId());

        if (student.getMajor().equals("计算机科学与技术")) {
            major.setSelection(0);
        } else if (student.getMajor().equals("物联网工程")) {
            major.setSelection(1);
        }

        if (student.getGender().equals("男")) {
            male.setChecked(true);
        } else {
            female.setChecked(true);
        }
    }

    private void showSelectedCourses(long id) {
        new ListSelectedCoursesGetTask().execute(id);
    }

    private void showSelectedCoursesImpl(List<Course> courses) {
        for (Course course : courses) {
            if (course.getCourseName() == computerNetwork.getText()) {
                computerNetwork.setChecked(true);
            }
            if (course.getCourseName() == javaProgramming.getText()) {
                javaProgramming.setChecked(true);
            }
            if (course.getCourseName() == embeddedSystems.getText()) {
                embeddedSystems.setChecked(true);
            }
        }
    }

    private void bindSubmitBtn() {
        submitButton.setOnClickListener(view -> {

        });
    }

    private class ListSelectedCoursesGetTask extends AsyncTask<Long, Void, String> {

        @Override
        protected String doInBackground(Long... ids) {
            return HttpConnect.getHttpResult(WebApiUrl.makeListCoursesUrl(ids[0]));
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(String result) {
            List<Course> courses = JsonUtils.toBean(result, new TypeReference<List<Course>>() {});

            if (courses != null) {
                showSelectedCoursesImpl(courses);
            }
        }
    }
}