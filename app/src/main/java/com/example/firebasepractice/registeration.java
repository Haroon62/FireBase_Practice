package com.example.firebasepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registeration extends AppCompatActivity {
    TextInputLayout t1,t2;
    TextView textView;
    ProgressBar progressBar;
    Button button;
    private FirebaseAuth auth;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        t1=findViewById(R.id.email);
        textView=findViewById(R.id.click);
        t2=findViewById(R.id.password);
        progressBar=findViewById(R.id.progress);
        button=findViewById(R.id.submit);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=t1.getEditText().getText().toString();
                String pass=t2.getEditText().getText().toString();
                auth = FirebaseAuth.getInstance();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(registeration.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(registeration.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(registeration.this, "Registered Successfully.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(), Home.class);
                                        startActivity(intent);
                                        finish();
                                    } else {

                                        Toast.makeText(registeration.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("errorsss", e.getMessage());
                                }
                            });
                }

            }
        });
    }
}