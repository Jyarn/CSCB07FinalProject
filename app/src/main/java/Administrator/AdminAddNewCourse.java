package Administrator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07final.R;
import com.example.cscb07final.databinding.AdminAddNewCourseBinding;

import java.util.HashSet;

public class AdminAddNewCourse extends AppCompatActivity {


    private AdminAddNewCourseBinding binding;
    EditText textAddCourseName;
    EditText textAddCourseCode;
    EditText textAddCourseSession;
    EditText textAddCoursePrerequisite;
    TextView textAddCoursePrerequisiteCount;
    TextView textAddCoursePrerequisiteDisplay;
    TextView textAddCourseSessionCount;
    TextView textAddCourseSessionDisplay;
    TextView textAdminMainInfo;
    View adminNewCourseView;
    Button buttonGoBack;
    Button buttonSaveNewCourse;
    Button buttonAddPrereq;
    Button buttonAddSession;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_new_course);
        HashSet<String> offeringSessions = new HashSet<String>();
        HashSet<String> prerequisites = new HashSet<String>();

        textAddCourseCode = (EditText) findViewById(R.id.editTextNewCourseCode);
        textAddCourseName = (EditText) findViewById(R.id.editTextNewCourseName);
        textAddCourseSession = (EditText) findViewById(R.id.editTextNewSession);
        textAddCoursePrerequisite = (EditText) findViewById(R.id.editTextNewPrerequisite);
        textAddCoursePrerequisiteCount = (TextView) findViewById(R.id.adminAddCoursePrerequisiteCount);
        textAddCoursePrerequisiteDisplay = (TextView) findViewById(R.id.adminAddCoursePrerequisiteDisplay);
        textAddCourseSessionCount = (TextView) findViewById(R.id.adminAddCourseSessionCount);
        textAddCourseSessionDisplay = (TextView) findViewById(R.id.adminAddCourseSessionDisplay);
        buttonGoBack = (Button) findViewById(R.id.returnAdminHomeButton) ;
        buttonSaveNewCourse = (Button) findViewById(R.id.adminSaveNewCourseButton);
        buttonAddPrereq = (Button) findViewById(R.id.adminNewCourseAddPrerequisiteButton);
        buttonAddSession = (Button) findViewById(R.id.adminNewCourseAddSessionButton);
        textAddCourseSessionCount.setText("0");
        textAddCoursePrerequisiteDisplay.setText("0");

        buttonAddPrereq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPrerequisiteText(prerequisites);
                textAddCoursePrerequisiteDisplay.setText(combineStrings(prerequisites));
            }
        });

        buttonAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSessionText(offeringSessions);
                textAddCourseSessionDisplay.setText(combineStrings(offeringSessions));
            }
        });

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent( AdminAddNewCourse.this, AdminMainActivity.class));
            }
        });


        buttonSaveNewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String courseCode;
                String courseName;
                if (String.valueOf(textAddCourseName.getText()) != null &&
                        String.valueOf(textAddCourseCode.getText()) != null){

                    addSessionText(offeringSessions);

                    courseName =  String.valueOf(textAddCourseName.getText());
                    courseCode = String.valueOf(textAddCourseCode.getText());

                    AdminCourseManager am = AdminCourseManager.getInstance();
                    am.setLastAction("Just added course: "+courseName+", "+courseCode);

                    Course newCourse = new Course(courseName, courseCode, offeringSessions, prerequisites);
                    am.addCourse(newCourse);

                    startActivity(new Intent( AdminAddNewCourse.this, AdminMainActivity.class));
                }
            }
        });
    }

    protected void addSessionText(HashSet<String> courseSessions){
        String session = String.valueOf(textAddCourseSession.getText());
        if (session.length() != 0) {
            textAddCourseSession.setText("");
            courseSessions.add(session);
            textAddCourseSessionCount.setText(String.valueOf(courseSessions.size()));
        }
    }

    protected void addPrerequisiteText(HashSet<String> prerequisites){
        String prereq = String.valueOf(textAddCoursePrerequisite.getText());
        if (prereq.length() != 0) {
            textAddCoursePrerequisite.setText("");
            prerequisites.add(prereq);
            textAddCoursePrerequisiteCount.setText(String.valueOf(prerequisites.size()));
        }
    }

    protected String combineStrings(HashSet<String> strings){
        String combinedString = "";
        int sessionNum = 0;
        for (String element : strings) {
            if(sessionNum>0)
                combinedString = combinedString + ", " + element;
            else{
                combinedString = element;
            }
            sessionNum++;
        }
        return combinedString;
    }
}
