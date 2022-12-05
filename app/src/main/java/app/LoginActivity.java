package app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cscb07final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import ddb.Database;
import login.Run;
import login.Student;

public class LoginActivity extends LoginView implements AdapterView.OnItemSelectedListener {
    LoginPresenter presenter;
    DatabaseReference firebaseDatabase;
    static Database currentUserDatabase;
    static DatabaseReference currentUserLogged;
    EditText email;
    EditText password;
    Button btnLogin;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textView = findViewById(R.id.signUp);
        email = findViewById(R.id.inputUserName);
        password = findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.loginButton);
        spinner = findViewById(R.id.spinnerId);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNextActivity();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
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


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.user, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        this.presenter = new LoginPresenter(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.setUserType(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void performAuthentication(CallBack callBack) {
        presenter.setMyEmail(email.getText().toString());
        presenter.setMyPassword(password.getText().toString());

        try {
            presenter.login(callBack);
        }

        catch (Exception err) {
            if (err.getMessage().equals("invalid email")) {
                email.setError("Invalid Email (no spaces at the end, or email must have @gmail.com)");
            }

            else if (err.getMessage().equals("invalid password")) {
                email.setError("Wrong Password");
            }
        }
    }


    public void makeToast (String msg) {
        Toast.makeText(LoginActivity.this, msg,Toast.LENGTH_SHORT ).show();
    }

    void toNextActivity () {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}