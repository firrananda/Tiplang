package dev.zero.tiplangpdam.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.service.SessionManager;

public class SplashScreenActivity extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sessionManager= new SessionManager(this);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){
                if (sessionManager.checkLogin())
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                else
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 2000);
    }
}
