package app;

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
    TextView textAddCourseFeedback;
    TextView textAddCourseSessionCount;
    TextView textAdminMainInfo;
    View adminNewCourseView;
//textAdminMainInfo
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
        HashSet<String> courseSessions = new HashSet<String>();

        textAddCourseCode = (EditText) view.findViewById(R.id.editTextNewCourseCode);
        textAddCourseName = (EditText) view.findViewById(R.id.editTextNewCourseName);
        textAddCourseSession = (EditText) view.findViewById(R.id.editTextNewSession);
        textAddCourseFeedback = (TextView) view.findViewById(R.id.adminAddCourseFeedback);
        textAdminMainInfo = (TextView) view.findViewById(R.id.textAdminMainInfo);
        textAddCourseSessionCount = (TextView) view.findViewById(R.id.adminAddCourseSessionCount);
        textAddCourseSessionCount.setText("0");

        binding.adminNewCourseAddSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSessionText(courseSessions);
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

                    addSessionText(courseSessions);

                    courseName =  String.valueOf(textAddCourseName.getText());
                    courseCode = String.valueOf(textAddCourseCode.getText());

                    AdminCourseManager am = AdminCourseManager.getInstance();
                    am.setAction("Just added course: "+courseName+", "+courseCode);

                    AdminCourse newCourse = new AdminCourse(courseCode, courseName, courseSessions);
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
            textAddCourseFeedback.setText(session);
            courseSessions.add(session);
            textAddCourseSessionCount.setText(String.valueOf(courseSessions.size()));
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
