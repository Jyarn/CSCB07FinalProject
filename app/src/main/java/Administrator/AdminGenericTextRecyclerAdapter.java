package Administrator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07final.R;

import java.util.ArrayList;

public class AdminGenericTextRecyclerAdapter extends RecyclerView.Adapter<AdminGenericTextRecyclerAdapter.RecViewHolders> {

    Context context;
    ArrayList<AdminGenericViewHolder> viewHolders;

    public AdminGenericTextRecyclerAdapter(Context newContext, ArrayList<AdminGenericViewHolder> newViewHolders){
        this.context = newContext;
        this.viewHolders = newViewHolders;
    }

    @NonNull
    @Override
    public RecViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recyler_course_row, parent, false);
        return new AdminGenericTextRecyclerAdapter.RecViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolders holder, int position) {
        holder.textView.setText(viewHolders.get(position).getViewHolderText());
    }

    public int getItemCount() {
        return viewHolders.size();
    }

    public static class RecViewHolders extends RecyclerView.ViewHolder {
        TextView textView;
        public RecViewHolders(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewAdminRecyclerGenericText);
        }
    }
}
