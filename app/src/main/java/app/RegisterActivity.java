package app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cscb07final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends LoginView {

    TextView alreadyHaveAcc;
    EditText email;
    EditText password;
    Button btnRegister;
    String possiblePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        alreadyHaveAcc = findViewById(R.id.haveAccount);
        email = findViewById(R.id.inputUserName);
        password = findViewById(R.id.inputPassword);
        btnRegister = findViewById(R.id.registerButton);




        TextView textView = findViewById(R.id.haveAccount);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performAuthentication(new CallBack() {
                    @Override
                    public Object callBack(Object ret) {
                        // enter functionality here
                        // if register returns true ret = true
                        // if register returns false ret = false
                        return null;
                    }
                });
            }
        });

        this.presenter = new LoginPresenter(this);
    }

    private void performAuthentication(CallBack callBack) {
        presenter.setMyEmail(email.getText().toString());
        presenter.setMyPassword(password.getText().toString());

        try {
            presenter.register(callBack);
        }

        catch (Exception err) {
            if (err.getMessage().equals("invalid email")) {
                email.setError("Invalid Email (no spaces at the end, or email must have @gmail.com)");
            }

            else if (err.getMessage().equals("invalid password")) {
                password.setError("Invalid Password");
            }
        }
    }

    void toNextActivity(){
        Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void makeToast (String msg) {
        Toast.makeText(RegisterActivity.this, msg,Toast.LENGTH_SHORT ).show();
    }

}