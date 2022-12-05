package app;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CountDownLatch;

public class LoginPresenter {
    LoginView view;
    LoginModel model;

    String myEmail;
    String myPassword;
    String userType;

    static boolean lock;
    static boolean r;

    String possiblePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public LoginPresenter (LoginView view) {
        this.view = view;
        this.model = new LoginModel(this);
        lock = true;
    }

    public void setUserType (String type) {
        this.userType = type;
    }

    public void setMyEmail (String myEmail) {
        this.myEmail = myEmail;
    }

    public void setMyPassword (String myPassword) {
        this.myPassword = myPassword;
    }

    public void register (CallBack callBack) throws Exception {
        if (validatePassword()) {
            model.register(myEmail, myPassword, new RegListener(new CallBack() {
                @Override
                public Object callBack(Object ret) {
                    if ((boolean)ret) {
                        view.makeToast("Registration Successful");
                        view.toNextActivity();
                        callBack.callBack(true);
                        return true;
                    }

                    else {
                        view.makeToast("Registration Failed");
                        callBack.callBack(false);
                        return false;
                    }
                }
            }));
        }
    }

    public void goToNextActivity(){
        if(userType.equalsIgnoreCase("admin")){
            Intent intent = new Intent(view,app.AdminActivity.class);
            view.startActivity(intent);
        }

        else{
            Intent intent2=new Intent(view,app.StudentActivity.class);
            view.startActivity(intent2);}
    }

    public boolean validatePassword () throws Exception {
        if (!myEmail.matches(possiblePattern)) {
            throw new Exception("invalid email");
        }

        else if (myPassword.isEmpty() || myPassword.length() < 6) {
            throw new Exception("invalid password");
        }

        return true;
    }

    public void login (CallBack callBack) throws Exception {
        if (validatePassword()) {
            model.authenticate(myEmail, myPassword, new AuthListener(new CallBack() {
                @Override
                public Object callBack(Object ret) {
                    if ((boolean)ret) {
                        goToNextActivity();
                        callBack.callBack(true);
                        return true;
                    }

                    else {
                        view.makeToast("login failed");
                        callBack.callBack(false);
                        return false;
                    }
                }
            }));
        }
    }

    public static void ping (boolean r) {
        lock = false;
    }
}
