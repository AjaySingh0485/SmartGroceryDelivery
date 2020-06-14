package com.smartgrocerydelivery.Network;

import com.google.gson.JsonObject;
import com.smartgrocerydelivery.Model.User;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/userdetails/OtherUsersLogin")
    Single<User>RegisterUser(@Body JsonObject locationPost);








    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/userdetails/verifyotp")
    Single<User>OTPverification(@Body JsonObject locationPost);




    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("api/OrderDelivery/GetAssignedOrders")
    Single<JsonObject>GetAssignedOrders(@Header("Authorization") String authToken, @Query("userId") Object locationPost);







    //http://182.156.211.186:8484/smartgrocery/api/



/*
    @FormUrlEncoded
    @POST("users/login")
    Single<User> register(@Field("email") String email, @Field("facebook_id") String facebook_id, @Field("password") String password
            , @Field("device_type") String device_type, @Field("device_id") String device_id);*/



}
