package app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07final.*;

import java.util.List;

public class AdminRecyclerViewAdapter extends RecyclerView.Adapter<AdminRecyclerViewHolder> {

    Context context;
    List<AdminCourse> courses;
    private AdminRecyclerViewHolder holder;
    private int position;

    public AdminRecyclerViewAdapter(Context newContext, List<AdminCourse> newCourses){
        this.context = newContext;
        this.courses = newCourses;

    }

    public AdminRecyclerViewAdapter(){

    }

    @NonNull
    @Override
    public AdminRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.admin_course_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminRecyclerViewHolder holder, int position) {
        holder.nameView.setText(courses.get(position).getName());
        holder.codeView.setText(courses.get(position).getCode());
        holder.sessionCountView.setText(courses.get(position).getSessionCount());
//        holder.iconView.setText(courses.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
