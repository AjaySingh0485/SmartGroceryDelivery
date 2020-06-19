package com.smartgrocerydelivery.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.smartgrocerydelivery.Activitys.ui.gallery.NewassingendFragment;
import com.smartgrocerydelivery.Activitys.ui.home.RuningFragment;
import com.smartgrocerydelivery.Activitys.ui.slideshow.SlideshowFragment;

import com.smartgrocerydelivery.Model.Itemdata.Parameter;
import com.smartgrocerydelivery.R;
import com.smartgrocerydelivery.savedata.SavePref;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    DrawerLayout drawer;
    NavigationView navigationView;
    ImageView menu_view;
    TextView hadder_text,runing_text,newassingend_text,deleverd_text;
    LinearLayout runing_view,newassingend_view,deleverd_view;
   public static List<Parameter> subitemdata ;
    public static int item_quanity=0 ;
    public static double item_price=0.0;

    public  static  String share_data="";

    SavePref savePref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        savePref=new SavePref();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        hadder_text = findViewById(R.id.hadder_text);
        menu_view = findViewById(R.id.menu_view);

        runing_view= findViewById(R.id.runing_view);
        newassingend_view= findViewById(R.id.newassingend_view);
        deleverd_view= findViewById(R.id.deleverd_view);


        runing_text= findViewById(R.id.runing_text);
        newassingend_text= findViewById(R.id.newassingend_text);
        deleverd_text= findViewById(R.id.deleverd_text);


        runing_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closenav();
                hadder_text.setText("Running");
                runing_text.setTextColor(getResources().getColor(R.color.gradienttwo));
                newassingend_text.setTextColor(getResources().getColor(R.color.fontcolor));
                deleverd_text.setTextColor(getResources().getColor(R.color.fontcolor));
                openhome();



            }
        });
        newassingend_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closenav();
                hadder_text.setText("Newly Assigned");
                runing_text.setTextColor(getResources().getColor(R.color.fontcolor));
                newassingend_text.setTextColor(getResources().getColor(R.color.gradienttwo));
                deleverd_text.setTextColor(getResources().getColor(R.color.fontcolor));
                openNewassingendFragment();
            }
        });
        deleverd_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closenav();
                hadder_text.setText("Delivered");
                runing_text.setTextColor(getResources().getColor(R.color.fontcolor));
                newassingend_text.setTextColor(getResources().getColor(R.color.fontcolor));
                deleverd_text.setTextColor(getResources().getColor(R.color.gradienttwo));
                openhomeSlideshowFragment();
            }


        });

        hadder_text.setText("Running");
        runing_text.setTextColor(getResources().getColor(R.color.gradienttwo));
        newassingend_text.setTextColor(getResources().getColor(R.color.fontcolor));
        deleverd_text.setTextColor(getResources().getColor(R.color.fontcolor));
openhome();


    }

    private void openhome() {

        Fragment fragCategory = null;
        fragCategory = new RuningFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
        transaction.replace(R.id.container, fragCategory);
        transaction.commit();
        closenav();

    }

    private void openhomeSlideshowFragment() {

        Fragment fragCategory = null;
        fragCategory = new SlideshowFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
        transaction.replace(R.id.container, fragCategory);
        transaction.commit();
        closenav();
    }

    private void openNewassingendFragment() {
        Fragment fragCategory = null;
        fragCategory = new NewassingendFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
        transaction.replace(R.id.container, fragCategory);
        transaction.commit();
        closenav();


    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        openbackpopup();

    }

    private void openbackpopup() {

        {
            closenav();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setCancelable(false);
            builder.setMessage("Are You Sure Want To Close This App ?");
            builder.setPositiveButton("Close", null);
            builder.setPositiveButton("Close",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            savePref.saveuser_token(HomeActivity.this,"");
                            savePref.saveuserId(HomeActivity.this,"");
                            finish();
                        }
                    });
            builder.setNegativeButton("Cancel", null);
            builder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menu = item.getItemId();
        switch (menu) {
            case R.id.action_settings:
                Intent i = new Intent(HomeActivity.this, SplashActivity.class);
                startActivity(i);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }


    public void logout(View view) {
        try {
            savePref.saveuser_token(HomeActivity.this,"");
            savePref.saveuserId(HomeActivity.this,"");

            Intent i = new Intent(HomeActivity.this, SplashActivity.class);
            startActivity(i);
            finish();
        } catch (Exception e) {

        }

    }

    public void openmenu(View view) {
        try {
            drawer.openDrawer(GravityCompat.START);
        } catch (Exception e) {

        }

    }
    public void closenav() {

        try {
            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception e) {
        }

    }
}