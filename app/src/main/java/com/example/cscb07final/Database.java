package com.example.cscb07final;

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

class readOnce implements OnCompleteListener<DataSnapshot> {
    public Object ret;
    public boolean lock;

    public readOnce () {
        this.ret = ":(";
        this.lock = true;
    }

    @Override
    public void onComplete(@NonNull Task<DataSnapshot> task) {
        this.ret = task.getResult().getValue();
        this.lock = false;
    }
}

public class Database {
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
            for (String i : newDir.split("/")) {
                if (i.equals("")) {
                    this.dir = dir.getRoot();
                }

                else if (i.equals("..")) {
                    this.dir = dir.getParent();
                }

                else {
                    this.dir = dir.child(i);
                }
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

            while (!task.isComplete()) {

            }

            return task.getResult().getValue();
        }

        catch (DatabaseException err) {
            Log.e("ddb", err.getMessage());
            return "err";
        }
    }
}
