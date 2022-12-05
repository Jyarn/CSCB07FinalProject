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



public class StudentActivity extends AppCompatActivity {
    Button addCourseBtn;
    EditText courseToBeAdded;
  String possiblePattern = "[a-zA-Z]+[0-9]";
  Button viewCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        addCourseBtn = findViewById(R.id.addStudentCourseId);
        courseToBeAdded = findViewById(R.id.courseTobeAddedId);
        viewCourse=findViewById(R.id.viewCourseStudentId);

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




        }


    }









