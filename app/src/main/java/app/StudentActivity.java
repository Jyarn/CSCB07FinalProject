package app;

import static app.LoginActivity.currentUserLogged;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07final.R;

import Administrator.AdminMainActivity;


public class StudentActivity extends AppCompatActivity {
    Button addCourseBtn;
    EditText courseToBeAdded;
  String possiblePattern = "[a-zA-Z]+[0-9]";
  Button viewCourse;
  Button logout;
  Button generateTimeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        addCourseBtn = findViewById(R.id.addStudentCourseId);
        courseToBeAdded = findViewById(R.id.courseTobeAddedId);
        viewCourse=findViewById(R.id.viewCourseStudentId);
        logout =(Button) findViewById(R.id.StudentLogout);
        generateTimeline=findViewById(R.id.generateCourseTimelineId);
        generateTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(app.StudentActivity.this,app.TimelineInputActivity.class);
                startActivity(intent);
            }
        });

        viewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(StudentActivity.this, ViewCourses.class);
                startActivity(intent);


            }
        });

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!courseToBeAdded.getText().toString().matches(possiblePattern) && courseToBeAdded.getText().toString().length() != 6) {
                    Toast.makeText(StudentActivity.this, "invalid course code", Toast.LENGTH_SHORT).show();
                } else {
                    currentUserLogged.child(courseToBeAdded.getText().toString()).child("courseCode").setValue(courseToBeAdded.getText().toString()) ;
                    Toast.makeText(StudentActivity.this, "course added successfully", Toast.LENGTH_SHORT).show();
                }
                currentUserLogged.getParent();

            }
               });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.auth.signOut();
                startActivity(new Intent(StudentActivity.this, LoginActivity.class));
            }
        });



        }


    }










