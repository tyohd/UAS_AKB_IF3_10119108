package com.example.diarynotesuasakb;
/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, OnBoardingActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}