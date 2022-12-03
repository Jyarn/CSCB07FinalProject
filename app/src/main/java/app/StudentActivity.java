package app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cscb07final.R;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //test start
        Intent intent=new Intent(app.StudentActivity.this,app.TimelineInputActivity.class);
        startActivity(intent);
        //test end
    }
}