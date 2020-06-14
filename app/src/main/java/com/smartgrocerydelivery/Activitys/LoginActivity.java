package com.smartgrocerydelivery.Activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
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
    Button login_text;
    TextView forgot_password;
    private ApiInterface apiService;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        init();
    }

    public void init() {
        spinner_issue = findViewById(R.id.spinner_issue);
        login_edit_email = findViewById(R.id.login_edit_email);
        login_edit_password = findViewById(R.id.login_edit_password);
        login_text = findViewById(R.id.login_text);
        forgot_password = findViewById(R.id.forgot_password);

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



    private void loginuser() {
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("userData", getdata());
            paramObject.addProperty("FCNToken", "KzkxfDEyMzQ1Njc4MDZ8QWRtaW5AMTIz");
            paramObject.addProperty("loginRequest", "DELIVERYEXEC");

        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.d("TAG@123", paramObject.toString());


        disposable.add(
                apiService.RegisterUser(paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<User>() {
                            @Override
                            public void onSuccess(User user) {

                                if (user.getSuccess()) {


                                    Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
                                    Log.d("TAG@123", "Success: " + user.getMessage());
                                    Log.d("TAG@123", "Success: " + user.getParameters().getRole());
                                    Intent i = new Intent(LoginActivity.this, OtpverificationActivity.class);
                                    startActivity(i);
                                    finish();

                                } else {


                                    Phone_number=login_edit_email.getText().toString().trim();
                                    OTP_number=user.getParameters().getOtp();
                                    Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
                                    Log.d("TAG@123", "onError Status false: " + user.getMessage());
                                    Intent i = new Intent(LoginActivity.this, OtpverificationActivity.class);
                                    startActivity(i);
                                    finish();
                                }

                                // pbProcessing.setVisibility(View.INVISIBLE);
                              /*  if (user.getStatus()) {
                                    savePref.saveuserId(Login.this, user.getUser().getId());
                                    savePref.saveusername(Login.this, user.getUser().getName());
                                    savePref.saveuser_email(Login.this, user.getUser().getEmail());
                                    savePref.saveuser_type(Login.this, user.getUser().getUserType());
                                    savePref.saveuser_pic(Login.this, user.getUser().getProfilePic());
                                    savePref.saveLoggedIn(Login.this, "true");
                                    Toast.makeText(Login.this, user.getMessage(), Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login.this, Welcome.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, user.getMessage(), Toast.LENGTH_LONG).show();

                                }*/
                            }

                            @Override
                            public void onError(Throwable e) {
                                // pbProcessing.setVisibility(View.INVISIBLE);
                                Log.d("TAG@123", "onError: " + e.getMessage());
                                Toast.makeText(LoginActivity.this, "Getting Some Error,Please Try Later..", Toast.LENGTH_LONG).show();

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