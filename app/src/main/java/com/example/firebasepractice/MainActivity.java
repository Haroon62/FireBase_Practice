package com.example.firebasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    TextView textView;
    FirebaseUser user;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button=findViewById(R.id.button);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        textView=findViewById(R.id.textemail);
        if (user==null){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
        else
        {
            textView.setText(user.getEmail());
        }
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();

        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(MainActivity.this,gso);
        googleSignInClient.signOut();
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();
    }
});
    }
}