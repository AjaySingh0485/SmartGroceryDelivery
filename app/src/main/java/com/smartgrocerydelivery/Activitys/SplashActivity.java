package com.smartgrocerydelivery.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.smartgrocerydelivery.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            } /*{

                if (!Const.isNetworkAvailable(Splash.this)) {
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "Network Not Available", Snackbar.LENGTH_LONG);
                    snackbar.show();}
                else {
                    if (readPref.getuserId().equalsIgnoreCase("")) {
                        Intent i = new Intent(Splash.this, User_Type.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Intent i = new Intent(Splash.this,Home.class);
                        startActivity(i);
                        finish();
                    }}

            }*/
        }, 1000);







    }
}