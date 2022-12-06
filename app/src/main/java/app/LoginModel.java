package app;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CountDownLatch;

interface CallBack {
    Object callBack(Object ret);
}

class AuthListener implements OnCompleteListener<AuthResult> {
    boolean lock;
    boolean result;
    CallBack clb;


    public AuthListener (CallBack clb) {
        lock = true;
        result = false;
        this.clb = clb;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance("https://cscb07finalproject-default-rtdb.firebaseio.com/").getReference();
            LoginActivity.currentUserLogged = firebaseDatabase.child("Students").child(task.getResult().getUser().getUid());
            result = true;
        }

        else {
            result = false;
        }

        clb.callBack(result);
    }
}

class RegListener implements OnCompleteListener<AuthResult> {
    boolean lock;
    boolean result;
    CallBack clb;


    public RegListener (CallBack clb) {
        lock = true;
        result = false;
        this.clb = clb;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            result = true;
        }

        else {
            result = false;
        }

        clb.callBack(result);
    }
}

public class LoginModel {
    static int AUTHResult;
    static String ret;
    FirebaseAuth auth;
    FirebaseUser currentUser;

    LoginPresenter presenter;

    public LoginModel (LoginPresenter presenter) {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        this.presenter = presenter;
    }

    public void authenticate (String myEmail, String myPassword, AuthListener a) {
        auth.signInWithEmailAndPassword(myEmail, myPassword).addOnCompleteListener(a);
    }

    public void register (String myEmail, String myPassword, RegListener rgl) {
        auth.createUserWithEmailAndPassword(myEmail, myPassword).addOnCompleteListener(rgl);
    }
}
