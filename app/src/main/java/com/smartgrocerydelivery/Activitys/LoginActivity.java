package com.smartgrocerydelivery.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.JsonObject;
import com.smartgrocerydelivery.MainActivity;
import com.smartgrocerydelivery.Model.User;
import com.smartgrocerydelivery.Network.ApiClient;
import com.smartgrocerydelivery.Network.ApiInterface;
import com.smartgrocerydelivery.Network.Const;
import com.smartgrocerydelivery.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.smartgrocerydelivery.Network.Const.OTP_number;
import static com.smartgrocerydelivery.Network.Const.Phone_number;

public class LoginActivity extends AppCompatActivity {

    Spinner spinner_issue;
    EditText login_edit_email, login_edit_password;

    TextView forgot_password;
    private ApiInterface apiService;
    private CompositeDisposable disposable = new CompositeDisposable();

    TextView login_hadder;
    RelativeLayout forgot_main;
    Button submit_text,back_text;
    Button login_text;
    Boolean login_flag=true;
    LinearLayout password_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG@123", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                         Const.token = task.getResult().getToken();
                        Log.d("TAG@123","Token  ---"+ Const.token);
                        // Log and toast


                       /// Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        init();
    }

    public void init() {
        spinner_issue = findViewById(R.id.spinner_issue);
        login_edit_email = findViewById(R.id.login_edit_email);
        login_edit_password = findViewById(R.id.login_edit_password);
        login_text = findViewById(R.id.login_text);
        forgot_password = findViewById(R.id.forgot_password);
        login_hadder = findViewById(R.id.login_hadder);
         forgot_main= findViewById(R.id.forgot_main);
        submit_text= findViewById(R.id.submit_text);
        back_text= findViewById(R.id.back_text);
        password_main= findViewById(R.id.password_main);


        login_hadder.setText("DELIVERY EXECUTIVE LOGIN");
        login_flag=true;
        forgot_main.setVisibility(View.GONE);

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_flag=false;
                login_hadder.setText("Forgot Password");
                forgot_main.setVisibility(View.VISIBLE);
                login_text.setVisibility(View.GONE);
                password_main.setVisibility(View.GONE);
                forgot_password.setVisibility(View.GONE);
            }
        });
        back_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_flag=true;
                login_hadder.setText("DELIVERY EXECUTIVE LOGIN");
                forgot_main.setVisibility(View.GONE);
                login_text.setVisibility(View.VISIBLE);
                password_main.setVisibility(View.VISIBLE);
                forgot_password.setVisibility(View.VISIBLE);
            }
        });








        submit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Const.isNetworkAvailable(LoginActivity.this)) {
                    Snackbar.make(v, "Network Not Available", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    if (login_edit_email.getText().toString().trim().length() != 10) {
                        login_edit_email.setError(getString(R.string.error_invalid_email));
                    } else {

                        forgot();
                    }
                }
            }
        });



        login_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!Const.isNetworkAvailable(LoginActivity.this)) {
                    Snackbar.make(view, "Network Not Available", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    if (LoginDataOK()) {

                        loginuser();
                    }
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        login_hadder.setText("DELIVERY EXECUTIVE LOGIN");
        login_flag=true;
        forgot_main.setVisibility(View.GONE);
        login_text.setVisibility(View.VISIBLE);
        password_main.setVisibility(View.VISIBLE);
        forgot_password.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        login_hadder.setText("DELIVERY EXECUTIVE LOGIN");
        login_flag=true;
        forgot_main.setVisibility(View.GONE);
        login_text.setVisibility(View.VISIBLE);
        password_main.setVisibility(View.VISIBLE);
        forgot_password.setVisibility(View.VISIBLE);



    }

    private void forgot(){
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("mobileNumber", login_edit_email.getText().toString().trim());
            paramObject.addProperty("countryCode", "+91");
            paramObject.addProperty("userType", "delivery");

        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.d("TAG@123", paramObject.toString());
        Const.startprogress(this);

        disposable.add(
                apiService.Forgot(paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                            @Override
                            public void onSuccess(JsonObject user) {
                                Const.finish_dialog();

                                Log.d("TAG@123",user.toString());
                                if(user.getAsJsonObject().get("responseCode").getAsInt()==200){

                                    Phone_number=login_edit_email.getText().toString().trim();
                                    Intent i = new Intent(LoginActivity.this, OtpverificationActivity.class);
                                    i.putExtra("Flag","Forgot");
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(LoginActivity.this,user.getAsJsonObject().get("message").getAsString(),Toast.LENGTH_LONG).show();

                                }





                               /* if (user.getSuccess()) {
                                    // user.getResponseCode()==404
                                    Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();

                                } else {

                                }*/


                            }

                            @Override
                            public void onError(Throwable e) {
                                Const.finish_dialog();
                                Log.d("TAG@123", "onError: " + e.getMessage());
                                //Toast.makeText(LoginActivity.this, "Getting Some Error,Please Try Later..", Toast.LENGTH_LONG).show();

                            }
                        }));
    }

    private void loginuser() {
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("userData", getdata());
            paramObject.addProperty("FCNToken", Const.token);
            paramObject.addProperty("loginRequest", "DELIVERYEXEC");

        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.d("TAG@123", paramObject.toString());
        Const.startprogress(this);

        disposable.add(
                apiService.RegisterUser(paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<User>() {
                            @Override
                            public void onSuccess(User user) {
                                Const.finish_dialog();
                                if (user.getSuccess()) {
                                   // user.getResponseCode()==404
                                    Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();

                                } else {
                                    Phone_number=login_edit_email.getText().toString().trim();
                                    OTP_number=user.getParameters().getOtp();
                                   // Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
                                    Log.d("TAG@123", "onError Status false: " + user.getMessage());
                                    Intent i = new Intent(LoginActivity.this, OtpverificationActivity.class);
                                    i.putExtra("Flag","Login");
                                    startActivity(i);
                                    finish();
                                }


                            }

                            @Override
                            public void onError(Throwable e) {
                                Const.finish_dialog();
                                Log.d("TAG@123", "onError: " + e.getMessage());
                                //Toast.makeText(LoginActivity.this, "Getting Some Error,Please Try Later..", Toast.LENGTH_LONG).show();

                            }
                        }));
    }


    public boolean LoginDataOK() {
        boolean isOk;
        if (login_edit_email.getText().toString().trim().equals("") || login_edit_email.getText().toString().equals("Email")) {
            login_edit_email.setError("Enter Mobile");
            isOk = false;
        } else {
            if (login_edit_password.getText().toString().trim().equals("")) {
                login_edit_password.setError("Enter Password");
                isOk = false;
            } else {
                if (login_edit_email.getText().toString().trim().length() != 10) {
                    login_edit_email.setError(getString(R.string.error_invalid_email));
                    isOk = false;
                } else {
                    isOk = true;

                }

            }
        }
        return isOk;
    }


    public String getdata() {
        byte[] data = new byte[0];
        try {
            String number = login_edit_email.getText().toString().trim();
            String password = login_edit_password.getText().toString().trim();
            String originalInput = "+91|" + number + "|" + password;
            data = originalInput.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return android.util.Base64.encodeToString(data, android.util.Base64.NO_WRAP);
    }


}