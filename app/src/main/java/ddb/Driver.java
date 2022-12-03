package ddb;

import app.MainActivity;

public class Driver {
    public void print (Object pnt) {
        if (pnt != null) {
            MainActivity.funnyText.setText(pnt.toString());
        }

        else {
            MainActivity.funnyText.setText("null");
        }
    }

    public Driver () {

    }

    public void main () {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "test");
        ddb.write("hi");
        print(ddb.read());
    }
}
