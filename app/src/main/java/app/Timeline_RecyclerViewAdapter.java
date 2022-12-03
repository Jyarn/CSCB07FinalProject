package app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07final.R;

import java.util.ArrayList;

public class Timeline_RecyclerViewAdapter extends RecyclerView.Adapter<Timeline_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<TimelineModel> timelineModels;

    public Timeline_RecyclerViewAdapter(Context context, ArrayList<TimelineModel> timelineModels) {
        this.context = context;
        this.timelineModels = timelineModels;
    }

    @NonNull
    @Override
    public Timeline_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.timetable_recycler_view_row, parent, false);
        return new Timeline_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Timeline_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvSession.setText(timelineModels.get(position).getSession());
        holder.tvCourses.setText(timelineModels.get(position).getCourses());
    }

    @Override
    public int getItemCount() {
        return timelineModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvSession, tvCourses;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSession = itemView.findViewById(R.id.sessionTextView);
            tvCourses = itemView.findViewById(R.id.coursesTextView);

        }
    }
}
