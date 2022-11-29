package Administrator;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07final.R;

import java.util.ArrayList;

public class AdminCourseRecyclerAdapter extends RecyclerView.Adapter<AdminCourseRecyclerAdapter.AdminCourseRecyclerViewHolder> {
    Context context;
    ArrayList<AdminRecyclerViewHolder> viewHolders;

    public AdminCourseRecyclerAdapter(Context newContext, ArrayList<AdminRecyclerViewHolder> newViewHolders) {
        AdminCourseManager acm = AdminCourseManager.getInstance();
        acm.setDebugText("AdminCourseRecylcerAdapter constructor called");
        this.context = newContext;
        this.viewHolders = newViewHolders;
    }

    @NonNull
    @Override //will inflate layout
    public AdminCourseRecyclerAdapter.AdminCourseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_course_view, parent, false);
        return new AdminCourseRecyclerViewHolder(view);
    }

    @Override //assigns values to views as they come onto screen
    public void onBindViewHolder(@NonNull AdminCourseRecyclerAdapter.AdminCourseRecyclerViewHolder holder, int position) {
        holder.tvCourseName.setText(viewHolders.get(position).getViewholderCourseName());
        holder.tvCourseCode.setText(viewHolders.get(position).getViewholderCourseCode());
        holder.tvCourseSessionCount.setText(viewHolders.get(position).getViewholderSessionCount());
        holder.tvCoursePrerequisiteCount.setText(viewHolders.get(position).getViewholderPrerequisiteCount());
    }

    @Override//
    public int getItemCount() {
        return viewHolders.size();
    }

    public static class AdminCourseRecyclerViewHolder extends RecyclerView.ViewHolder {
        //gets views from list and assigns them to views
        //ImageView courseIcon;
        TextView tvCourseName;
        TextView tvCourseCode;
        TextView tvCourseSessionCount;
        TextView tvCoursePrerequisiteCount;

        public AdminCourseRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCourseName = itemView.findViewById(R.id.course_name);
            tvCourseCode = itemView.findViewById(R.id.course_code);
            tvCourseSessionCount = itemView.findViewById(R.id.course_sessions_count);
            tvCoursePrerequisiteCount = itemView.findViewById(R.id.course_prerequisite_count);
        }
    }
}
