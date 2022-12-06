package app;

import androidx.appcompat.app.AppCompatActivity;

public abstract class LoginView extends AppCompatActivity {
    public abstract void makeToast (String msg);
    abstract void toNextActivity ();
}
