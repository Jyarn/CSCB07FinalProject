package app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07final.R;

import java.util.ArrayList;
import java.util.HashMap;

import Administrator.AddCourse;
import Administrator.Course;
import Timeline.Course_Student;
import Timeline.Student;

public class TimelineActivity extends AppCompatActivity {

    Button tRButton;
    EditText courseInputEditText;
    ArrayList<TimelineModel> timelineModels = new ArrayList<TimelineModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //courseInputEditText = findViewById(R.id.courseInputEditText);
        tRButton = findViewById(R.id.tRButton);
        tRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TimelineActivity.this,app.TimelineInputActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.tRecyclerView);
        setUpTimelineModels(); // not connected to backend yet
        Timeline_RecyclerViewAdapter adapter = new Timeline_RecyclerViewAdapter(this, timelineModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpTimelineModels() {
        Student std = new Student(new ArrayList<Course_Student>());
        ArrayList<String> req = new ArrayList<String>();
        String[] courseList = TimelineInputActivity.courseList;

        for(String s: courseList) {
            req.add(s);
        }

        HashMap<String, ArrayList<String>> semesters = std.generateTimetable(req);
        //HashMap<String, ArrayList<String>> semesters = new HashMap<String, ArrayList<String>>();

        String coursesS;
        boolean first;
        int count = 0;
        for(String s1: semesters.keySet()) {
            coursesS = "";

            first = true;
            ArrayList<String> courses = semesters.get(s1);

            for(String s2: courses) {
                if(first == true) {
                    coursesS += s2;
                    first = false;
                    count ++;
                }
                else if(first == false && count % 3 == 0) {
                    coursesS += ", \n" + s2;
                    count++;
                }
                else {
                    coursesS += ", " + s2;
                    count ++;
                }
            }

            timelineModels.add(new TimelineModel(s1, coursesS));
        }

    }
}