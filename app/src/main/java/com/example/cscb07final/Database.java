package com.example.cscb07final;

import android.util.Log;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        
        return true;
    }
}
