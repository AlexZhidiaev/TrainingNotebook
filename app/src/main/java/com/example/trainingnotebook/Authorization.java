package com.example.trainingnotebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authorization extends AppCompatActivity {

    public static String userLog;
    //Button loginBtn;
    AppCompatButton loginButton;
    TextView loginView, passwordView;
    TextView registrationTxt;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_authorization);
        loginButton = (AppCompatButton) findViewById(R.id.loginbutton);
        registrationTxt = (TextView) findViewById(R.id.registrationText);
        loginView = (TextView)findViewById(R.id.LoginInput);
        passwordView = (TextView) findViewById(R.id.passwordInput);
        Click(loginView.getText().toString(), passwordView.getText().toString());
    }

    void Click(String log, String pas) {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log = loginView.getText().toString();

                String pas = passwordView.getText().toString();

                mAuth.signInWithEmailAndPassword(log, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {//
                            App.getInstance().setLogin(log);
                            Intent intent = new Intent(Authorization.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Authorization.this, "Wrong password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        });

        registrationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Authorization.this, Registration.class);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}