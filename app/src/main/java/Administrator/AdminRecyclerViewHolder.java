package Administrator;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cscb07final.*;

//public class AdminRecyclerViewHolder extends RecyclerView.ViewHolder  {
public class AdminRecyclerViewHolder{

//        ImageView imageView;
    String viewholderCourseName;
    String viewholderCourseCode;
    int viewholderSessionCount;
    int viewholderPrerequisiteCount;

    public AdminRecyclerViewHolder(String viewholderCourseName, String viewholderCourseCode, int viewholderSessionCount, int viewholderPrerequisiteCount) {
        //super(itemView);
        this.viewholderCourseName = viewholderCourseName;
        this.viewholderCourseCode = viewholderCourseCode;
        this.viewholderSessionCount = viewholderSessionCount;
        this.viewholderPrerequisiteCount = viewholderPrerequisiteCount;
    }

    public String getViewholderCourseName() {
        return viewholderCourseName;
    }

    public String getViewholderCourseCode() {
        return viewholderCourseCode;
    }

    public int getViewholderSessionCount() {
        return viewholderSessionCount;
    }

    public int getViewholderPrerequisiteCount() {
        return viewholderPrerequisiteCount;
    }
}
