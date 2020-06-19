package com.smartgrocerydelivery.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.JsonObject;
import com.smartgrocerydelivery.Model.User;
import com.smartgrocerydelivery.Network.ApiClient;
import com.smartgrocerydelivery.Network.ApiInterface;
import com.smartgrocerydelivery.Network.Const;
import com.smartgrocerydelivery.R;
import com.smartgrocerydelivery.savedata.SavePref;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.smartgrocerydelivery.Network.Const.OTP_number;
import static com.smartgrocerydelivery.Network.Const.Phone_number;
import static com.smartgrocerydelivery.Network.Const.Token;
import static com.smartgrocerydelivery.Network.Const.user_id;

public class OtpverificationActivity extends AppCompatActivity {
    private ApiInterface apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    EditText login_edit_otp;
    Button otp_text, submit_text;
    SavePref savePref;
    private static final int REQ_USER_CONSENT = 200;
    SmsBroadcastReceiver smsBroadcastReceiver;
    boolean first_flag = true;
    TextView verifly_otp;


//SmsBroadcastReceiver

    LinearLayout enter_otp_main, password_main;
    EditText edit_password, confirm_edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        first_flag = true;
        savePref = new SavePref();
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        login_edit_otp = findViewById(R.id.login_edit_otp);
        otp_text = findViewById(R.id.otp_text);

        enter_otp_main = findViewById(R.id.enter_otp_main);
        password_main = findViewById(R.id.password_main);
        edit_password = findViewById(R.id.edit_password);
        confirm_edit_password = findViewById(R.id.confirm_edit_password);
        submit_text = findViewById(R.id.submit_text);
        verifly_otp = findViewById(R.id.verifly_otp);
        password_main.setVisibility(View.GONE);
        enter_otp_main.setVisibility(View.VISIBLE);
        submit_text.setVisibility(View.GONE);
        otp_text.setVisibility(View.VISIBLE);
        verifly_otp.setText("VERIFY OTP");

        otp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (login_edit_otp.getText().toString().trim().equals("")) {
                    login_edit_otp.setError("Enter OTP");

                } else if (login_edit_otp.getText().toString().trim().length() != 6) {
                    login_edit_otp.setError(getString(R.string.error_invalid_otp));
                } else {


                    if (getIntent().getStringExtra("Flag").equals("Forgot")) {

                        otp_forgotverificationuser();
                    } else {

                        otpverificationuser();
                    }


                }

            }
        });


        submit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_password.getText().toString().trim().equals("")) {
                    edit_password.setError("Enter Password");

                } else if (confirm_edit_password.getText().toString().trim().equals("")) {
                    confirm_edit_password.setError("Enter Password");

                } else if (!edit_password.getText().toString().trim().equals(confirm_edit_password.getText().toString().trim())) {

                    confirm_edit_password.setError("Password Not Match");

                } else {

                    update_password();


                }

            }
        });


        startSmsUserConsent();
    }

    private void startSmsUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Toast.makeText(getApplicationContext(), "On Success", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Toast.makeText(getApplicationContext(), "On OnFailure", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_USER_CONSENT) {
            if ((resultCode == RESULT_OK) && (data != null)) {


                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                Log.d("TAG@123", message);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                // otp_text.setText(String.format("%s - %s", getString(R.string.received_message), message));
                getOtpFromMessage(message);


            }
        }
    }

    private void getOtpFromMessage(String message) {
        // This will match any 6 digit number in the message
        Pattern pattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            login_edit_otp.setText(matcher.group(0));


            if (getIntent().getStringExtra("Flag").equals("Forgot")) {

                otp_forgotverificationuser();
            } else {

                otpverificationuser();
            }




        }
    }

    private void registerBroadcastReceiver() {
        smsBroadcastReceiver = new SmsBroadcastReceiver();
        smsBroadcastReceiver.smsBroadcastReceiverListener = new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
            @Override
            public void onSuccess(Intent intent) {
                startActivityForResult(intent, REQ_USER_CONSENT);
            }

            @Override
            public void onFailure() {
            }
        };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }


    public void update_password() {


        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("userData", getdata());


        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG@123", paramObject.toString());
        Const.startprogress(this);

        disposable.add(
                apiService.Forgot_password_update(paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                            @Override
                            public void onSuccess(JsonObject user) {
                                Const.finish_dialog();
                                if (user.getAsJsonObject().get("responseCode").getAsInt() == 200) {
                                    onBackPressed();
                                } else {
                                    Toast.makeText(OtpverificationActivity.this, user.getAsJsonObject().get("message").getAsString(), Toast.LENGTH_LONG).show();

                                }
                                //


                            }

                            @Override
                            public void onError(Throwable e) {

                                Const.finish_dialog();

                                Log.d("TAG@123", "onError: " + e.getMessage());

                            }
                        }));
    }


    public void otp_forgotverificationuser() {
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("Phone", Phone_number);
            paramObject.addProperty("countryCode", "+91");
            paramObject.addProperty("OTP", Integer.parseInt(login_edit_otp.getText().toString().trim()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG@123", paramObject.toString());
        Const.startprogress(this);
        disposable.add(apiService.OTP_Forgot_verification(paramObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject user) {
                        Const.finish_dialog();
                        if (user.getAsJsonObject().get("responseCode").getAsInt() == 200) {
                            password_main.setVisibility(View.VISIBLE);
                            enter_otp_main.setVisibility(View.GONE);
                            submit_text.setVisibility(View.VISIBLE);
                            otp_text.setVisibility(View.GONE);
                            verifly_otp.setText("Reset Password");

                        } else {
                            Toast.makeText(OtpverificationActivity.this, user.getAsJsonObject().get("message").getAsString(), Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Const.finish_dialog();
                        Log.d("TAG@123", "onError: " + e.getMessage());


                    }
                }));
    }


    public void otpverificationuser() {
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("Code", "91");
            paramObject.addProperty("FCNToken", "KzkxfDEyMzQ1Njc4MDZ8QWRtaW5AMTIz");
            paramObject.addProperty("Phone", Phone_number);
            paramObject.addProperty("OTP", Integer.parseInt(login_edit_otp.getText().toString().trim()));

        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.d("TAG@123", paramObject.toString());
        Const.startprogress(this);

        disposable.add(apiService.OTPverification(paramObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                        Const.finish_dialog();
                        if (user.getResponseCode() == 200) {
                            Log.d("TAG@123", "Success: " + user.getMessage());
                            Log.d("TAG@123", "Success: " + user.getParameters().getRole());
                            Token = (String) user.getParameters().getToken();
                            user_id = user.getParameters().getUserId();
                            savePref.saveuser_token(OtpverificationActivity.this, (String) user.getParameters().getToken());
                            savePref.saveuserId(OtpverificationActivity.this, String.valueOf(user.getParameters().getUserId()));
                            Intent i = new Intent(OtpverificationActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(OtpverificationActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Const.finish_dialog();
                        Log.d("TAG@123", "onError: " + e.getMessage());

                    }
                }));
    }


    public String getdata() {
        byte[] data = new byte[0];
        try {

            String password = edit_password.getText().toString().trim();
            String originalInput = "+91|" + Phone_number + "|" + password;
            data = originalInput.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return android.util.Base64.encodeToString(data, android.util.Base64.NO_WRAP);
    }


}