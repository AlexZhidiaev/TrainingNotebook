package com.example.trainingnotebook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    TextView loginView, passwordView, confirmPasswordView;
    Button registrationButton;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://zhidyaevdb-default-rtdb.europe-west3.firebasedatabase.app/");
    public DatabaseReference root =db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_registration);
        registrationButton =(Button) findViewById(R.id.regNewAccountText);
        loginView =(TextView)findViewById(R.id.LoginInput);
        passwordView =(TextView)findViewById(R.id.passwordInput);
        confirmPasswordView =(TextView)findViewById(R.id.passwordConfirm);

        Click();
    }
    private void Click(){

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log = loginView.getText().toString();
                String pas = passwordView.getText().toString();
                String pasRep = confirmPasswordView.getText().toString();
                if (eqPas(pas,pasRep)) {
                    mAuth.createUserWithEmailAndPassword(log, pas)
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                private static final String TAG ="UserDB";

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        HashMap<String,String> userMap = new HashMap<>();
                                        userMap.put("login",log);
                                        userMap.put("password",pas);
                                        System.out.println(userMap);
                                        String id = root.push().getKey();
                                        root.child(id).setValue(userMap);
                                    } else {
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(Registration.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

    }
    public static boolean eqPas(String password, String repeatPassword){
        if (password.equals(repeatPassword))
            return true;
        else
            return false;
    }
}
