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

public class RegisterActivity extends AppCompatActivity {

    TextView alreadyHaveAcc;
    EditText email;
    EditText password;
    Button btnRegister;
    String possiblePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth auth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        alreadyHaveAcc = findViewById(R.id.haveAccount);
        email = findViewById(R.id.inputUserName);
        password = findViewById(R.id.inputPassword);
        btnRegister = findViewById(R.id.registerButton);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();


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
            auth.createUserWithEmailAndPassword(myEmail,myPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        toNextActivity();
                        Toast.makeText(RegisterActivity.this ,"Registration successful",Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(RegisterActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }
    }

    private void toNextActivity(){
        Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}