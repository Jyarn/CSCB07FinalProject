package Administrator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

//import com.example.adminmainsection01.databinding.AdminAddNewCourseBinding;
//import com.example.app.databinding.AdminAddNewCourseBinding;

import com.example.cscb07final.*;
import com.example.cscb07final.databinding.AdminAddNewCourseBinding;

import java.util.HashSet;

public class AdminAddNewCourse extends Fragment {

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

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        this.adminNewCourseView = inflater.inflate(R.layout.admin_add_new_course, container, false);
        binding = AdminAddNewCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // AdminAddCourseFeedback
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HashSet<String> offeringSessions = new HashSet<String>();
        HashSet<String> prerequisites = new HashSet<String>();

        textAddCourseCode = (EditText) view.findViewById(R.id.editTextNewCourseCode);
        textAddCourseName = (EditText) view.findViewById(R.id.editTextNewCourseName);
        textAddCourseSession = (EditText) view.findViewById(R.id.editTextNewSession);
        textAddCoursePrerequisite = (EditText) view.findViewById(R.id.editTextNewPrerequisite);
        textAddCoursePrerequisiteCount = (TextView) view.findViewById(R.id.adminAddCoursePrerequisiteCount);
        textAddCoursePrerequisiteDisplay = (TextView) view.findViewById(R.id.adminAddCoursePrerequisiteDisplay);
        textAdminMainInfo = (TextView) view.findViewById(R.id.admin_last_action_textview);
        textAddCourseSessionCount = (TextView) view.findViewById(R.id.adminAddCourseSessionCount);
        textAddCourseSessionDisplay = (TextView) view.findViewById(R.id.adminAddCourseSessionDisplay);
        textAddCourseSessionCount.setText("0");
        textAddCoursePrerequisiteDisplay.setText("0");

        binding.adminNewCourseAddPrerequisiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPrerequisiteText(prerequisites);
                textAddCoursePrerequisiteDisplay.setText(combineStrings(prerequisites));
            }
        });

        binding.adminNewCourseAddSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSessionText(offeringSessions);
                textAddCourseSessionDisplay.setText(combineStrings(offeringSessions));
            }
        });

        binding.returnAdminHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AdminAddNewCourse.this)
                        .navigate(R.id.action_AdminAddNewCourseFragment_to_AdminHomeFragment);
            }
        });


        binding.adminSaveNewCourseButton.setOnClickListener(new View.OnClickListener() {
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

                    Course newCourse = new Course(courseCode, courseName, offeringSessions, prerequisites);
                    am.addCourse(newCourse);
                    NavHostFragment.findNavController(AdminAddNewCourse.this)
                            .navigate(R.id.action_AdminAddNewCourseFragment_to_AdminHomeFragment);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
