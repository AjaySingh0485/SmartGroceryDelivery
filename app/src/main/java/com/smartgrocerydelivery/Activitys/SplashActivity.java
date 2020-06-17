package com.smartgrocerydelivery.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.smartgrocerydelivery.Network.Const;
import com.smartgrocerydelivery.R;
import com.smartgrocerydelivery.savedata.ReadPref;
import com.smartgrocerydelivery.savedata.SavePref;

import static com.smartgrocerydelivery.Network.Const.Token;
import static com.smartgrocerydelivery.Network.Const.user_id;

public class SplashActivity extends AppCompatActivity {
    ReadPref readPref;
    SavePref savePref;
    LinearLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        coordinatorLayout=findViewById(R.id.coordinatorLayout);
        readPref = new ReadPref(this);
        savePref = new SavePref();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){


                if (!Const.isNetworkAvailable(SplashActivity.this)) {
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "Network Not Available", Snackbar.LENGTH_LONG);
                    snackbar.show();}
                else {
                    if (readPref.getuserId().equalsIgnoreCase("")) {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Token = readPref.getuser_token();
                        user_id =Integer.parseInt(readPref.getuserId());
                        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }}





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