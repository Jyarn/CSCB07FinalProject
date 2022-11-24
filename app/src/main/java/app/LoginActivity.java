package app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button btnLogin;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    String possiblePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textView = findViewById(R.id.signUp);
        email = findViewById(R.id.inputUserName);
        password = findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.loginButton);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performAuthentication();
            }
        });
    }

    private void performAuthentication() {

        String myEmail = email.getText().toString();
        String myPassword = password.getText().toString();
        if (!myEmail.matches(possiblePattern)) {
            email.setError("invalid email");
        } else if (myPassword.isEmpty() || myPassword.length() < 6) {
            password.setError("invalid password");
        } else {
            auth.signInWithEmailAndPassword(myEmail,myPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        goToNextActivity();

                    }
                    else{

                        Toast.makeText(LoginActivity.this,"login failed",Toast.LENGTH_SHORT ).show();

                    }
                }
            });
        }

    }

    private void goToNextActivity() {
        Intent intent=new Intent(app.LoginActivity.this,app.HomePage.class);
        startActivity(intent);
    }
}