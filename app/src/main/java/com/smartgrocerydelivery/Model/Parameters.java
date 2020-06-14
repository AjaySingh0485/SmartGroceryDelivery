
package com.smartgrocerydelivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("phone_no")
    @Expose
    private Object phoneNo;
    @SerializedName("last_updated_kitchen")
    @Expose
    private Integer lastUpdatedKitchen;
    @SerializedName("kitchen_id")
    @Expose
    private Integer kitchenId;
    @SerializedName("kitchen_name")
    @Expose
    private Object kitchenName;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("token")
    @Expose
    private Object token;
    @SerializedName("fcnToken")
    @Expose
    private Object fcnToken;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("language")
    @Expose
    private Object language;
    @SerializedName("vendorId")
    @Expose
    private Integer vendorId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Object phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getLastUpdatedKitchen() {
        return lastUpdatedKitchen;
    }

    public void setLastUpdatedKitchen(Integer lastUpdatedKitchen) {
        this.lastUpdatedKitchen = lastUpdatedKitchen;
    }

    public Integer getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(Integer kitchenId) {
        this.kitchenId = kitchenId;
    }

    public Object getKitchenName() {
        return kitchenName;
    }

    public void setKitchenName(Object kitchenName) {
        this.kitchenName = kitchenName;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getFcnToken() {
        return fcnToken;
    }

    public void setFcnToken(Object fcnToken) {
        this.fcnToken = fcnToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getLanguage() {
        return language;
    }

    public void setLanguage(Object language) {
        this.language = language;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

}
