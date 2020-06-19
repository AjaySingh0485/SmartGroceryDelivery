package com.smartgrocerydelivery.Network;

import com.google.gson.JsonObject;
import com.smartgrocerydelivery.Model.Itemdata.Subitemdata;
import com.smartgrocerydelivery.Model.Orderdata.Orderitemdata;
import com.smartgrocerydelivery.Model.User;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
    @POST("api/userdetails/resetpassword")
    Single<JsonObject>Forgot_password_update(@Body JsonObject locationPost);


////////////////////////////

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/userdetails/forgotpasswordotp")
    Single<JsonObject>OTP_Forgot_verification(@Body JsonObject locationPost);

    /////////////////
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/UserDetails/forgotpassword")
    Single<JsonObject>Forgot(@Body JsonObject locationPost);

   /* @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("api/OrderDelivery/GetAssignedOrders")
    Single<JsonObject>GetAssignedOrders( @Query("userId") Integer apiKey);*/


   /* @GET("api/OrderDelivery/GetAssignedOrders")
    Single<JsonObject>GetAssignedOrders(@Path("userId") int groupId);*/


   /* @Headers("Content-Type: application/json")
    @HTTP(method = "GET", path = "api/OrderDelivery/GetAssignedOrders", hasBody = true)
    Single<JsonObject>GetAssignedOrders(@Body JsonObject locationPost);*/



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/OrderDelivery/GetAssignedOrders")
    Single<Orderitemdata>GetAssignedOrders(@Header("Authorization") String authToken, @Body JsonObject locationPost);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/OrderDelivery/getpickedorder")
    Single<Orderitemdata>getpickedorder(@Header("Authorization") String authToken, @Body JsonObject locationPost);

    //
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/OrderDelivery/getdeliveredorder")
    Single<Orderitemdata>getdeliveredorder(@Header("Authorization") String authToken, @Body JsonObject locationPost);





    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/Orders/getorderdescription")
    Single<Subitemdata>getorderdescription(@Header("Authorization") String authToken, @Body JsonObject locationPost);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/OrderDelivery/updateorderstatus")
    Single<JsonObject>updateorderstatus(@Header("Authorization") String authToken, @Body JsonObject locationPost);














  /*

    @HTTP(method = "GET", path = "api/OrderDelivery/GetAssignedOrders", hasBody = true)
    Single<JsonObject>GetAssignedOrders(@Header("Authorization") String authToken,@Body JsonObject locationPost);*/



    //http://182.156.211.186:8484/smartgrocery/api/



/*
    @FormUrlEncoded
    @POST("users/login")
    Single<User> register(@Field("email") String email, @Field("facebook_id") String facebook_id, @Field("password") String password
            , @Field("device_type") String device_type, @Field("device_id") String device_id);*/



}
