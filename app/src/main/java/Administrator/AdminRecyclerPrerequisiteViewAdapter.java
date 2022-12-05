package Administrator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07final.R;

import java.util.ArrayList;

public class AdminRecyclerPrerequisiteViewAdapter extends RecyclerView.Adapter<AdminRecyclerPrerequisiteViewAdapter.RecViewHolders> {

    private final AdminRecyclerPrerequisiteInterface adminRecyclerPrerequisiteInterface;

    Context context;
    ArrayList<AdminGenericViewHolder> viewHolders;

    public AdminRecyclerPrerequisiteViewAdapter(Context newContext, ArrayList<AdminGenericViewHolder> newViewHolders,
                                                AdminRecyclerPrerequisiteInterface adminRecyclerPrerequisiteInterface){
        this.context = newContext;
        this.viewHolders = newViewHolders;
        this.adminRecyclerPrerequisiteInterface = adminRecyclerPrerequisiteInterface;
    }

    @NonNull
    @Override
    public RecViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recyler_generic_row, parent, false);
        //return new RecViewHolders(view);
        return new AdminRecyclerPrerequisiteViewAdapter.RecViewHolders(view, adminRecyclerPrerequisiteInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolders holder, int position) {
        holder.textViewText.setText(viewHolders.get(position).getViewHolderText());
    }

    public int getItemCount() {
        return viewHolders.size();
    }

    public static class RecViewHolders extends RecyclerView.ViewHolder {
        TextView textViewText;
        ImageButton deleteButton;

        public RecViewHolders(@NonNull View itemView,
                              AdminRecyclerPrerequisiteInterface adminRecyclerPrerequisiteInterface) {
            super(itemView);
            textViewText = itemView.findViewById(R.id.textViewAdminRecyclerGenericText);
            deleteButton = itemView.findViewById(R.id.admin_recycler_delete_Button);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(adminRecyclerPrerequisiteInterface != null){
                        int position = getAbsoluteAdapterPosition();
                        String textValue = (String) textViewText.getText();

                        if (position != RecyclerView.NO_POSITION){
                            adminRecyclerPrerequisiteInterface.onClickToDeletePrerequisite(position, textValue);
                        }
                    }
                }
            });


        }
    }
}
