package app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07final.R;

public class TimelineInputActivity extends AppCompatActivity {

    Button tIRButton;
    Button gTButton;
    EditText courseInputEditText;
    static String[] courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_input);

        courseInputEditText = findViewById(R.id.courseInputEditText);
        tIRButton = findViewById(R.id.tIRButton);
        tIRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TimelineInputActivity.this, StudentActivity.class);
                startActivity(intent1);
            }
        });

        gTButton = findViewById(R.id.gTButton);
        gTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateTimeline();
            }
        });

    }

    private void generateTimeline() {
        courseList = courseInputEditText.getText().toString().split(" ");
        //need to implement backend here
        Intent intent2 = new Intent(TimelineInputActivity.this, TimelineActivity.class);
        startActivity(intent2);
    }
}