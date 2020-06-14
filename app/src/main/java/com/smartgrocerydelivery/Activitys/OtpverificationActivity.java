package com.smartgrocerydelivery.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.smartgrocerydelivery.Model.User;
import com.smartgrocerydelivery.Network.ApiClient;
import com.smartgrocerydelivery.Network.ApiInterface;
import com.smartgrocerydelivery.R;

import java.io.UnsupportedEncodingException;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otpverification);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        login_edit_otp=findViewById(R.id.login_edit_otp);
        otp_text=findViewById(R.id.otp_text);
        login_edit_otp.setText(""+OTP_number);
        otp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (login_edit_otp.getText().toString().trim().equals("")) {
                    login_edit_otp.setError("Enter OTP");

                } else  if (login_edit_otp.getText().toString().trim().length() !=6) {
                    login_edit_otp.setError(getString(R.string.error_invalid_otp));
                }else {

                    otpverificationuser();
                }

            }
        });
    }



    public  void otpverificationuser(){
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("Code", "91");
            paramObject.addProperty("FCNToken", "KzkxfDEyMzQ1Njc4MDZ8QWRtaW5AMTIz");
            paramObject.addProperty("Phone",Phone_number);
            paramObject.addProperty("OTP",OTP_number);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.d("TAG@123", paramObject.toString());


        disposable.add(
                apiService.OTPverification(paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<User>() {
                            @Override
                            public void onSuccess(User user){





                                Toast.makeText(OtpverificationActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
                                Log.d("TAG@123", "Success: " + user.getMessage());
                                Log.d("TAG@123", "Success: " + user.getParameters().getRole());
                                Token=(String)user.getParameters().getToken();
                                user_id =user.getParameters().getUserId();
                                Intent i = new Intent(OtpverificationActivity.this, HomeActivity.class);
                                startActivity(i);
                                finish();


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
                                // pbProcessing.setVisibility(View.INVISIBLE);
                                Log.d("TAG@123", "onError: " + e.getMessage());
                                Toast.makeText(OtpverificationActivity.this, "Getting Some Error,Please Try Later..", Toast.LENGTH_LONG).show();

                            }
                        }));
    }










}