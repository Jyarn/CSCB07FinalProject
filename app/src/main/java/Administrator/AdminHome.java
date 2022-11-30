package Administrator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07final.*;
import com.example.cscb07final.databinding.AdminHomeBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AdminHome extends Fragment {

    private AdminHomeBinding binding;
    RecyclerView recyclerView;
    TextView textAdminMainCourseCount;
    TextView textAdminLastAction;
    TextView textDebugInfo;
    List<Course> courseList = new ArrayList<Course>();
    ArrayList<AdminRecyclerViewHolder> recyclerHolders = new ArrayList<AdminRecyclerViewHolder>();

    AdminCourseManager acm = AdminCourseManager.getInstance();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.admin_home, container, false);
        recyclerView = view.findViewById(R.id.admin_recycler_view);

        for(Course course: acm.allCourses){
            courseList.add(course);
        }
//
//        HashSet<String> dummy = new HashSet<String>();
//        Course newC1 = new Course("Calculus 1", "MATA01", dummy, dummy);
//        AddCourse.addCourse(newC1);
//        HashSet<String> sessions1 = new HashSet<String>();
//        sessions1.add("Winter2023");
//        Course newC2 = new Course("Discrete math", "MATA03", sessions1, dummy);
//        AddCourse.addCourse(newC2);

//        Course x = new Course("Software Design", "CSCB07");
//        Course y = new Course("Introduction to Computer Science II", "CSCA48");
//        x.addOfferingSession("Fall");
//        x.addOfferingSession("Winter");
//        x.addOfferingSession("Summer");
//        x.addPrerequisite(y);
//        AddCourse.addCourse(x);
//        AddCourse.addCourse(y);


//        String courseInfo = "";
        HashSet<Course> allCourses = ReadCourse.readAllCourses();
//        Log.e("something",String.valueOf(allCourses.size()));
        acm.setDebugText(String.valueOf(allCourses.size()));
//        int timesRead = 0;
//        for(Course cors: allCourses){
//            courseInfo = courseInfo + cors.toString() + " - " ;
//            timesRead++;
//        }
//        acm.setDebugText(courseInfo);
//        acm.setDebugText(String.valueOf(timesRead));

        setupAdminRecyclerViewHolders();

        AdminCourseRecyclerAdapter courseRecycleradapter = new AdminCourseRecyclerAdapter(getContext(), recyclerHolders);
        recyclerView.setAdapter(courseRecycleradapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        acm.setLastAction("Total list length: "+courseList.size());

        //examples returns view instead of binding
        binding = AdminHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textAdminMainCourseCount = (TextView) view.findViewById(R.id.admin_main_course_count_textview);
        textAdminMainCourseCount.setText("Total courses: "+acm.courseCount());
        textAdminLastAction = (TextView) view.findViewById(R.id.admin_last_action_textview);
        textAdminLastAction.setText(acm.lastAction);
        textDebugInfo = (TextView) view.findViewById(R.id.admin_main_debug_text);
        textDebugInfo.setText(acm.debugText);

        //HashSet<Course> allCourses = ReadCourse.readAllCourses();

        binding.adminLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AdminHome.this)
                        .navigate(R.id.action_AdminHomeFragment_to_FirstFragment);
            }
        });

        binding.adminAddNewCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AdminHome.this)
                        .navigate(R.id.action_AdminHomeFragment_to_AdminAddNewCourse);
            }
        });

        binding.adminEditCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AdminHome.this)
                        .navigate(R.id.action_AdminHomeFragment_to_AdminEditCourse);
            }
        });
    }

    private void setupAdminRecyclerViewHolders(){
        AdminCourseManager acm = AdminCourseManager.getInstance();
        for (Course course: acm.allCourses){
            //recyclerHolders.add(new AdminRecyclerViewHolder(course.name, course.courseCode, course.getSessionCount(),course.getPrerequisiteCount()));
            recyclerHolders.add(new AdminRecyclerViewHolder(course.name, course.courseCode, course.offeringSessions.size(),course.prerequisites.size()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}