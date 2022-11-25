package ddb;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {
    final String url = "https://cscb07finalproject-default-rtdb.firebaseio.com/";
    private DatabaseReference dir;

    public Database (String url) {
        this.dir = FirebaseDatabase.getInstance(url).getReference();
    }

    public Database (String url, String node) {
        this.dir = FirebaseDatabase.getInstance(url).getReference(node);
    }

    public boolean write (Object val) {
        try {
            dir.setValue(val);
            return true;
        }

        catch (DatabaseException err) {
            Log.e("ddb", err.getMessage());
            return false;
        }
    }

    public boolean cd (String newDir) {
        try {
            boolean c = true;

            for (String i : newDir.split("/")) { // refactor maybe?
                if (i.equals("") && c) {
                    this.dir = dir.getRoot();
                }

                else if (i.equals("..")) {
                    this.dir = dir.getParent();
                }

                else {
                    this.dir = dir.child(i);
                }

                c = false;
            }
        }

        catch (DatabaseException err) {
            Log.e("ddb", err.getMessage());
            return false;
        }

        return true;
    }

    public Object read () {
        try {
            Task<DataSnapshot> task = dir.get();

            while (!task.isComplete()) {}

            return task.getResult().getValue();
        }

        catch (DatabaseException err) {
            Log.e("ddb", err.getMessage());
            return "err";
        }
    }

    public boolean remove () {
        try {
            dir.removeValue();
        }

        catch (DatabaseException err) {
            Log.e("ddb", err.getMessage());
        }
        return true;
    }
}
