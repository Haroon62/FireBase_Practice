package com.example.firebasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash_screan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screan);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences shrd=getSharedPreferences("login",MODE_PRIVATE);
             Boolean check=shrd.getBoolean("flag",false);
                Intent intent;

             if (check){
                 intent=new Intent(Splash_screan.this, Home.class);


             }else {
                 intent=new Intent(Splash_screan.this,Login.class);
             }
             startActivity(intent)   ;
            }
        },4000);

    }
}