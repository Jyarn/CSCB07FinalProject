package app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ddb.Database;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseReference firebaseDatabase;

static Database currentUserDatabase;
    static DatabaseReference currentUserLogged;
    EditText email;
    EditText password;
    Button btnLogin;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    String possiblePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Spinner spinner;
    String userType;
    String myEmail;

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
        spinner = findViewById(R.id.spinnerId);
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
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.user, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userType=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void performAuthentication() {

        myEmail = email.getText().toString();
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
                        firebaseDatabase=FirebaseDatabase.getInstance("https://cscb07finalproject-default-rtdb.firebaseio.com/").getReference();
                       currentUserLogged= firebaseDatabase.child("Students").child(task.getResult().getUser().getUid());
                        goToNextActivity();
                    }

                    else{

                        Toast.makeText(LoginActivity.this,"login failed",Toast.LENGTH_SHORT ).show();

                    }
                }
            });
        }


    }

    private void goToNextActivity(){

        if(userType.equalsIgnoreCase("admin")){
            Intent intent=new Intent(LoginActivity.this,Administrator.AdminMainActivity.class);
            startActivity(intent);}
        else{
            Intent intent2=new Intent(LoginActivity.this, StudentActivity.class);
            startActivity(intent2);}

    }
}