package app;

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
import java.util.List;

public class AdminHome extends Fragment {

    private AdminHomeBinding binding;
    private RecyclerView recyclerView;
    TextView textAdminMainCourseCount;
    TextView textAdminMainInfo;
    List<AdminCourse> courseList = new ArrayList<AdminCourse>();

    AdminCourseManager acm = AdminCourseManager.getInstance();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.admin_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
//
//        HashSet<String> sessions = new HashSet<>();
//        sessions.add("01");
//        sessions.add("02");
//        AdminCourse test1 = new AdminCourse("b032","Frat", sessions );
//        AdminCourse test2 = new AdminCourse("a025","Sup", sessions );
//        acm.addCourse(test1);
//        acm.addCourse(test2);

        for(AdminCourse course: acm.allCourses){
            courseList.add(course);
        }

        AdminRecyclerViewAdapter adapter = new AdminRecyclerViewAdapter(getContext(), courseList);
        LinearLayoutManager manager = new LinearLayoutManager((getContext()));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        AdminCourseManager acm = AdminCourseManager.getInstance();
        //acm.setAction("Total list length: "+courseList.size());

        binding = AdminHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textAdminMainCourseCount = (TextView) view.findViewById(R.id.textAdminMainCourseCount);
        textAdminMainCourseCount.setText("Total courses: "+acm.courseCount());
        textAdminMainInfo = (TextView) view.findViewById(R.id.textAdminMainInfo);
        textAdminMainInfo.setText(acm.lastAction);

        acm.setAction("Total list length: "+acm.courseCount());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}