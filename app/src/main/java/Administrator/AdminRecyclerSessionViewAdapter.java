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

public class AdminRecyclerSessionViewAdapter extends RecyclerView.Adapter<AdminRecyclerSessionViewAdapter.RecViewHolders> {

    private final AdminRecyclerSessionInterface adminRecyclerSessionInterface;

    Context context;
    ArrayList<AdminGenericViewHolder> viewHolders;

    public AdminRecyclerSessionViewAdapter(Context newContext, ArrayList<AdminGenericViewHolder> newViewHolders,
                                           AdminRecyclerSessionInterface adminRecyclerSessionInterface){
        this.context = newContext;
        this.viewHolders = newViewHolders;
        this.adminRecyclerSessionInterface = adminRecyclerSessionInterface;
    }

    @NonNull
    @Override
    public RecViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recyler_generic_row, parent, false);
        //return new RecViewHolders(view);
        return new AdminRecyclerSessionViewAdapter.RecViewHolders(view, adminRecyclerSessionInterface);
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
                              AdminRecyclerSessionInterface adminRecyclerSessionInterface) {
            super(itemView);
            textViewText = itemView.findViewById(R.id.textViewAdminRecyclerGenericText);
            deleteButton = itemView.findViewById(R.id.admin_recycler_delete_Button);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(adminRecyclerSessionInterface != null){
                        int position = getAbsoluteAdapterPosition();
                        String textValue = (String) textViewText.getText();

                        if (position != RecyclerView.NO_POSITION){
                            adminRecyclerSessionInterface.onClickToDeleteSession(position, textValue);
                        }
                    }
                }
            });


        }
    }
}
