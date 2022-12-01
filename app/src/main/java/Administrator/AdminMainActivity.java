package Administrator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cscb07final.*;
import com.example.cscb07final.databinding.AdminMainActivityBinding;


import java.util.ArrayList;
import java.util.HashSet;

public class AdminMainActivity extends AppCompatActivity {

    private AdminMainActivityBinding binding;
    ArrayList<AdminCourseViewHolder> adminCourseViewHolders = new ArrayList<AdminCourseViewHolder>();
    AdminCourseManager acm;
    HashSet<Course> coursesHashSet;
    TextView textAdminMainCourseCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_activity);

        textAdminMainCourseCount = (TextView) findViewById(R.id.text_admin_course_count);
        AdminCourseManager acm = AdminCourseManager.getInstance();
        coursesHashSet = acm.getCopyOfCourses();
        textAdminMainCourseCount.setText("Total courses: "+acm.courseCount());

        RecyclerView recyclerView = findViewById(R.id.admin_course_recycler_view);
        setupAdminCourseViewHolders();
        AdminCourseRecyclerViewAdapter recyclerAdapter = new AdminCourseRecyclerViewAdapter(this, adminCourseViewHolders);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button adminLogout = (Button) findViewById(R.id.admin_main2_logout_button);
        Button adminAddNew = (Button) findViewById(R.id.admin_main2_add_new_button);
        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this, app.MainActivity.class));
            }
        });
        adminAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this, AdminAddNewCourse.class));
            }
        });
    }

    private void setupAdminCourseViewHolders(){
        for (Course course: coursesHashSet){
            //recyclerHolders.add(new AdminRecyclerViewHolder(course.name, course.courseCode, course.getSessionCount(),course.getPrerequisiteCount()));
            adminCourseViewHolders.add(new AdminCourseViewHolder(course.name, course.courseCode));
        }
    }
}
