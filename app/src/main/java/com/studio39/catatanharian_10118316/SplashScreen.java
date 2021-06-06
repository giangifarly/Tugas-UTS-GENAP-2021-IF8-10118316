package com.studio39.catatanharian_10118316;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    //Tanggal Pengerjaan 5 Juni 2021, 10118316, Gian Gifarly, IF8

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mainMenu();
            }
        }, 2000);
    }

    private void mainMenu(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}