package Administrator;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cscb07final.*;


public class AdminRecyclerViewHolder extends RecyclerView.ViewHolder  {

//        ImageView imageView;
        TextView nameView;
        TextView codeView;
        TextView sessionCountView;

        public AdminRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView = itemView.findViewById(R.id.course_icon);
            nameView = itemView.findViewById(R.id.course_name);
            codeView = itemView.findViewById(R.id.course_code);
            sessionCountView = itemView.findViewById(R.id.course_sessions_count);
        }

//    public ImageView getImageView() {
//            return imageView;
//    }
    public TextView getnameView(){
            return nameView;
    }
    public TextView getcodeView(){
            return codeView;
    }
    public TextView sessionCountView(){
            return sessionCountView;
    }
}
