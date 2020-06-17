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
    Button otp_text;
    SavePref savePref;
    private static final int REQ_USER_CONSENT = 200;
    SmsBroadcastReceiver smsBroadcastReceiver;
//SmsBroadcastReceiver



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otpverification);
        savePref = new SavePref();
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        login_edit_otp = findViewById(R.id.login_edit_otp);
        otp_text = findViewById(R.id.otp_text);
        otp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (login_edit_otp.getText().toString().trim().equals("")) {
                    login_edit_otp.setError("Enter OTP");

                } else if (login_edit_otp.getText().toString().trim().length() != 6) {
                    login_edit_otp.setError(getString(R.string.error_invalid_otp));
                } else {

                    otpverificationuser();
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
                Log.d("TAG@123",message);
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
            otpverificationuser();


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

        disposable.add(
                apiService.OTPverification(paramObject)
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
                                    savePref.saveuser_token(OtpverificationActivity.this,(String) user.getParameters().getToken());
                                    savePref.saveuserId(OtpverificationActivity.this,String.valueOf(user.getParameters().getUserId()));
                                    Intent i = new Intent(OtpverificationActivity.this, HomeActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(OtpverificationActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
                                }
                                //


                            } /*{

                                if (user.getSuccess()) {


                                    Toast.makeText(OtpverificationActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
                                    Log.d("TAG@123", "Success: " + user.getMessage());
                                    Log.d("TAG@123", "Success: " + user.getParameters().getRole());
                                    Intent i = new Intent(OtpverificationActivity.this, OtpverificationActivity.class);
                                    startActivity(i);
                                    finish();

                                } else {
                                    Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
                                    Log.d("TAG@123", "onError Status false: " + user.getMessage());
                                    Intent i = new Intent(LoginActivity.this, OtpverificationActivity.class);
                                    startActivity(i);
                                    finish();
                                }


                            }*/

                            @Override
                            public void onError(Throwable e) {

                                Const.finish_dialog();
                                // pbProcessing.setVisibility(View.INVISIBLE);
                                Log.d("TAG@123", "onError: " + e.getMessage());
                                //Toast.makeText(OtpverificationActivity.this, "Getting Some Error,Please Try Later..", Toast.LENGTH_LONG).show();

                            }
                        }));
    }


}