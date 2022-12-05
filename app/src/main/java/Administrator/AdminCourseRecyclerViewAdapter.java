package Administrator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.cscb07finalproject.R;
import com.example.cscb07final.*;

import java.util.ArrayList;

public class AdminCourseRecyclerViewAdapter extends RecyclerView.Adapter<AdminCourseRecyclerViewAdapter.RecViewHolders> {

    private final AdminCourseEditInterface adminCourseEditInterface;

    Context context;
    ArrayList<AdminCourseViewHolder> viewHolders;

    public AdminCourseRecyclerViewAdapter(Context newContext, ArrayList<AdminCourseViewHolder> newViewHolders,
                                          AdminCourseEditInterface adminCourseEditInterface){
        this.context = newContext;
        this.viewHolders = newViewHolders;
        this.adminCourseEditInterface = adminCourseEditInterface;
    }

    @NonNull
    @Override
    public RecViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recyler_course_row, parent, false);
        //return new RecViewHolders(view);
        return new AdminCourseRecyclerViewAdapter.RecViewHolders(view, adminCourseEditInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolders holder, int position) {
        holder.tvCourseName.setText(viewHolders.get(position).getViewholderCourseName());
        holder.tvCourseCode.setText(viewHolders.get(position).getViewholderCourseCode());
//        holder.tvCourseSessionCount.setText(viewHolders.get(position).getViewholderSessionCount());
//        holder.tvCoursePrerequisiteCount.setText(viewHolders.get(position).getViewholderPrerequisiteCount());
    }

    public int getItemCount() {
        return viewHolders.size();
    }

    public static class RecViewHolders extends RecyclerView.ViewHolder {
        //gets views from list and assigns them to views
        //ImageView courseIcon;
        TextView tvCourseName;
        TextView tvCourseCode;
        ImageButton editButton;
//        TextView tvCourseSessionCount;
//        TextView tvCoursePrerequisiteCount;

        public RecViewHolders(@NonNull View itemView, AdminCourseEditInterface adminCourseEditInterface) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.textViewAdminRecyclerGenericTitle);
            tvCourseCode = itemView.findViewById(R.id.textViewRecyclerCourseCode);
//            tvCourseSessionCount = itemView.findViewById(R.id.course_sessions_count);
//            tvCoursePrerequisiteCount = itemView.findViewById(R.id.course_prerequisite_count);

            editButton = itemView.findViewById(R.id.admin_course_recycler_edit_button);

            editButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(adminCourseEditInterface != null){
                        int position = getAbsoluteAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            adminCourseEditInterface.onClickToEdit(position);
                        }
                    }
                    //Intent courseEditIntent = new Intent(AdminMainActivity.this, AdminEditCourse.class);
                }
            });

        }
    }
}
