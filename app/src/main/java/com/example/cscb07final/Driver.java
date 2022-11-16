package com.example.cscb07final;

import android.util.Log;

import java.util.ArrayList;

public class Driver {
    public Driver () {

    }

    public void main () {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "some");

        ddb.write("what's a matter baby");
    }
}
