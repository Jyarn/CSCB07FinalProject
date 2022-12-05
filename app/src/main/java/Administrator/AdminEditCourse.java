package Administrator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07final.R;
import com.example.cscb07final.databinding.AdminEditCourseBinding;

import java.util.ArrayList;
import java.util.HashSet;

public class AdminEditCourse extends AppCompatActivity implements AdminRecyclerSessionInterface, AdminRecyclerPrerequisiteInterface{

//    private AdminEditCourseBinding binding;

    AdminGenericTextRecyclerAdapter sessionGenRecyclerAdapter;
    AdminGenericTextRecyclerAdapter prereqGenRecyclerAdapter;
    AdminRecyclerSessionViewAdapter sessionRecyclerAdapter;
    AdminRecyclerPrerequisiteViewAdapter prereqRecyclerAdapter;
    ArrayList<AdminGenericViewHolder> adminEditSessionViewHolders;
    ArrayList<AdminGenericViewHolder> adminEditPrerequisiteViewHolders;
    HashSet<String> newSessionsSet;
    HashSet<String> newPrereqsSet;

    Button buttonGoBack;
    Button buttonSaveEditedCourse;
    Button buttonAddPrereq;
    Button buttonAddSession;
    ImageButton buttonDeleteCourse;

    String courseCode;

    Course oldCourseVersion;
    Course newCourseVersion;
    String newCourseCode;
    String newCourseName;
    String newSession;
    String newPrerequisite;

    EditText TV_CourseCodeDisplay;
    EditText TV_CourseNameDisplay;
    EditText TV_newSession;
    EditText TV_newPrereq;
    TextView TV_debug;

    Context tempContext;
    AdminCourseManager acm = AdminCourseManager.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_course);
        buttonGoBack = (Button) findViewById(R.id.returnAdminHomeButton) ;
        buttonSaveEditedCourse = (Button) findViewById(R.id.adminSaveEditedCourseButton);
        buttonAddPrereq = (Button) findViewById(R.id.adminEditCourseAddPrereqButton);
        buttonAddSession = (Button) findViewById(R.id.adminEditCourseAddSessionButton);
        buttonDeleteCourse = (ImageButton) findViewById(R.id.adminEditDeleteCourseButton);

        courseCode = getIntent().getStringExtra("Code");
        oldCourseVersion = ReadCourse.readCourse(courseCode);

        if (newSessionsSet == null || newPrereqsSet == null){
            newSessionsSet = (HashSet<String>) oldCourseVersion.offeringSessions.clone();
            newPrereqsSet = (HashSet<String>) oldCourseVersion.prerequisites.clone();
        }
        TV_CourseNameDisplay = (EditText) findViewById(R.id.editTextEditCourseName);
        TV_CourseNameDisplay.setText(oldCourseVersion.name);
        TV_CourseCodeDisplay = (EditText) findViewById(R.id.editTextEditCourseCode);
        TV_CourseCodeDisplay.setText(oldCourseVersion.courseCode);
        TV_newSession = (EditText) findViewById(R.id.editTextAdmintNewSession);
        TV_newPrereq = (EditText) findViewById(R.id.editTextAdninNewPrerequisite);

        TV_debug = (TextView) findViewById(R.id.textViewEditDebug);
        TV_debug.setText(acm.debugText);
////
//        RecyclerView prereqRecyclerView = findViewById(R.id.adminEditRecyclerPrereq);
//        adminEditPrerequisiteViewHolders = setupGenericViewHolders(newPrereqsSet);
//        AdminGenericTextRecyclerAdapter prereqGenRecyclerAdapter = new AdminGenericTextRecyclerAdapter(this, adminEditPrerequisiteViewHolders);
//        prereqRecyclerView.setAdapter(prereqGenRecyclerAdapter);
//        prereqRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        RecyclerView sessionRecyclerView = findViewById(R.id.adminEditRecyclerSession);
//        adminEditSessionViewHolders = setupGenericViewHolders(newSessionsSet);
//        AdminGenericTextRecyclerAdapter sessionGenRecyclerAdapter = new AdminGenericTextRecyclerAdapter(this, adminEditSessionViewHolders);
//        sessionRecyclerView.setAdapter(sessionGenRecyclerAdapter);
//        sessionRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView prereqRecyclerView = findViewById(R.id.adminEditRecyclerPrereq);
        adminEditPrerequisiteViewHolders = setupGenericViewHolders(newPrereqsSet);
        AdminRecyclerPrerequisiteViewAdapter prereqRecyclerAdapter = new AdminRecyclerPrerequisiteViewAdapter(this, adminEditPrerequisiteViewHolders, this);
        prereqRecyclerView.setAdapter(prereqRecyclerAdapter);
        prereqRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView sessionRecyclerView = findViewById(R.id.adminEditRecyclerSession);
        adminEditSessionViewHolders = setupGenericViewHolders(newSessionsSet);
        AdminRecyclerSessionViewAdapter sessionRecyclerAdapter = new AdminRecyclerSessionViewAdapter(this, adminEditSessionViewHolders, this);
        sessionRecyclerView.setAdapter(sessionRecyclerAdapter);
        sessionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tempContext = getApplicationContext();

        buttonAddPrereq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               acm.setDebugText("add prereq clicked: ");
               newPrerequisite = String.valueOf(TV_newPrereq.getText());
               TV_newPrereq.setText("");
               newPrereqsSet.add(newPrerequisite);
               prereqRecyclerAdapter.notifyDataSetChanged();
               //blink();

            }
        });
        buttonAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acm.setDebugText("add session clicked: ");
                newSession = String.valueOf(TV_newSession.getText());
                TV_newSession.setText("");
                newSessionsSet.add(newSession);

                sessionRecyclerAdapter.notifyDataSetChanged();
               // blink();
            }
        });
        buttonDeleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                acm.setDebugText("delete course clicked: ");
                DeleteCourse.deleteCourse(courseCode);
                acm.removeCourse(oldCourseVersion);
                //acm.allCourses.remove(oldCourseVersion);
                startActivity(new Intent(AdminEditCourse.this, AdminMainActivity.class));
            }
        });
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminEditCourse.this, AdminMainActivity.class));
            }
        });
        buttonSaveEditedCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newCourseCode = String.valueOf(TV_CourseCodeDisplay.getText());
                newCourseName = String.valueOf(TV_CourseNameDisplay.getText());
                newCourseVersion = new Course(newCourseName, newCourseCode, newSessionsSet, newPrereqsSet);

                EditCourse.replaceCourse(newCourseVersion, oldCourseVersion.getCourseCode());

                acm.updateCourses();
                startActivity(new Intent(AdminEditCourse.this, AdminMainActivity.class));
            }
        });
    }

    private ArrayList<AdminGenericViewHolder> setupGenericViewHolders(HashSet<String> stringHashSet){
        ArrayList<AdminGenericViewHolder> viewHolders = new ArrayList<AdminGenericViewHolder>();
        for (String text: stringHashSet){
            viewHolders.add(new AdminGenericViewHolder(text));
        }
        return viewHolders;
    }

    @Override
    public void onClickToDeletePrerequisite(int position, String textValue) {

        acm.setDebugText("pre req delete clicked: "+textValue);
        newPrereqsSet.remove(textValue);
        adminEditPrerequisiteViewHolders.remove(position);
        prereqRecyclerAdapter.notifyItemRemoved(position);
        prereqRecyclerAdapter.notifyDataSetChanged();
        //blink();

    }

    @Override
    public void onClickToDeleteSession(int position, String textValue) {

        acm.setDebugText("pre req delete clicked: "+textValue);
        newSessionsSet.remove(textValue);
        adminEditSessionViewHolders.remove(position);
        sessionRecyclerAdapter.notifyItemRemoved(position);
        sessionRecyclerAdapter.notifyDataSetChanged();
        //blink();


    }
    private void blink(){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}