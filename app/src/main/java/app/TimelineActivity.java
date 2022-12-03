package app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cscb07final.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TimelineActivity extends AppCompatActivity {

    Button tRButton;
    ArrayList<TimelineModel> timelineModels = new ArrayList<TimelineModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        tRButton = findViewById(R.id.tRButton);
        tRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(app.TimelineActivity.this,app.TimelineInputActivity.class);
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
        HashMap<String, ArrayList<String>> semesters = new HashMap<String, ArrayList<String>>();
        ArrayList<String> a = new ArrayList<String>();

        //test start
        a.add("CSCB07");
        a.add("CSCB09");
        a.add("CSCB09");
        a.add("CSCB09");
        a.add("CSCB10");
        semesters.put("Fall 2022", a);
        semesters.put("Winter 2023", a);
        //test end
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
                if(first == false && count % 3 == 0) {
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