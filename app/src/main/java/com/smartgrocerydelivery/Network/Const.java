package com.smartgrocerydelivery.Network;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import com.smartgrocerydelivery.R;


public class Const {


    public static final String BASE_URL = "http://182.156.211.186:8484/smartgrocery/";



//http://182.156.211.186:8484/smartgrocery/api/


    public static  String Phone_number = "";
    public static int OTP_number = 0;
    public static  String Token = "";
    public static  int user_id = 0;

    public static ProgressDialog dialog;
    public static final String PUBLISHABLE_KEY_live = "pk_test_tQ0xcc4SLTFoRsDIJLfYXKqy";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static void startprogress(final Context context) {
        dialog = new ProgressDialog(context, R.style.full_screen_dialog) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.full_dialog);
                getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                ImageView gifImageView = dialog.findViewById(R.id.custom_loading_imageView);

                Glide.with(context)
                        .load(R.drawable.logo_loder)
                        .into(gifImageView);

            }
        };




        dialog.setCancelable(true);
        dialog.show();
    }

    public static void finish_dialog() {
        try{
            if(dialog.isShowing()) {
                dialog.dismiss();
            }}
        catch (Exception e){

        }
    }





}
