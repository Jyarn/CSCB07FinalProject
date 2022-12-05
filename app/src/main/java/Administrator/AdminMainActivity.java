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

public class AdminMainActivity extends AppCompatActivity implements AdminCourseEditInterface  {

    private AdminMainActivityBinding binding;
    ArrayList<AdminCourseViewHolder> adminCourseViewHolders = new ArrayList<AdminCourseViewHolder>();
    AdminCourseManager acm;
    HashSet<Course> coursesHashSet;
    TextView textAdminMainCourseCount;
    TextView adminDebugText;
    AdminCourseRecyclerViewAdapter recyclerAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_activity);
        //blink();

        textAdminMainCourseCount = (TextView) findViewById(R.id.text_admin_course_count);
        AdminCourseManager acm = AdminCourseManager.getInstance();
        coursesHashSet = acm.getCopyOfCourses();
        textAdminMainCourseCount.setText("Total courses: "+acm.courseCount());
        adminDebugText = findViewById(R.id.admin_main_debug_text);
        adminDebugText.setText(acm.debugText);

        recyclerView = findViewById(R.id.admin_course_recycler_view);

        setupAdminCourseViewHolders();
        recyclerAdapter = new AdminCourseRecyclerViewAdapter(this, adminCourseViewHolders, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button adminLogout = (Button) findViewById(R.id.admin_main2_logout_button);
        Button adminAddNew = (Button) findViewById(R.id.admin_main2_add_new_button);
        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blink();
                startActivity(new Intent(AdminMainActivity.this, app.MainActivity.class));
            }
        });
        adminAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blink();
                startActivity(new Intent(AdminMainActivity.this, AdminAddNewCourse.class));
            }
        });
    }

    private void setupAdminCourseViewHolders(){
        for (Course course: coursesHashSet){
            adminCourseViewHolders.add(new AdminCourseViewHolder(course.name, course.courseCode));
        }
    }

    @Override
    public void onClickToEdit(int position) {
        blink();
        Intent editIntent = new Intent(AdminMainActivity.this, AdminEditCourse.class);
        editIntent.putExtra("Code", adminCourseViewHolders.get(position).getViewholderCourseCode());
        startActivity(editIntent);
    }

    private void blink(){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
