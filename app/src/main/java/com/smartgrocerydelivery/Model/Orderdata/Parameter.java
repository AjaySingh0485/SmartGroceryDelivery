
package com.smartgrocerydelivery.Model.Orderdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameter {

    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("vendersId")
    @Expose
    private Integer vendersId;
    @SerializedName("userMasterId")
    @Expose
    private Integer userMasterId;
    @SerializedName("customerAddress")
    @Expose
    private String customerAddress;
    @SerializedName("orderStatus")
    @Expose
    private Integer orderStatus;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("customerLat")
    @Expose
    private Double customerLat;
    @SerializedName("customerLong")
    @Expose
    private Double customerLong;
    @SerializedName("customerSectorId")
    @Expose
    private Integer customerSectorId;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerMobile")
    @Expose
    private String customerMobile;
    @SerializedName("venderName")
    @Expose
    private String venderName;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("shopNo")
    @Expose
    private String shopNo;
    @SerializedName("venderAddress")
    @Expose
    private Object venderAddress;
    @SerializedName("venderLat")
    @Expose
    private Double venderLat;
    @SerializedName("venderLong")
    @Expose
    private Double venderLong;
    @SerializedName("venderMobile")
    @Expose
    private String venderMobile;
    @SerializedName("venderSector")
    @Expose
    private Integer venderSector;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getVendersId() {
        return vendersId;
    }

    public void setVendersId(Integer vendersId) {
        this.vendersId = vendersId;
    }

    public Integer getUserMasterId() {
        return userMasterId;
    }

    public void setUserMasterId(Integer userMasterId) {
        this.userMasterId = userMasterId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Double getCustomerLat() {
        return customerLat;
    }

    public void setCustomerLat(Double customerLat) {
        this.customerLat = customerLat;
    }

    public Double getCustomerLong() {
        return customerLong;
    }

    public void setCustomerLong(Double customerLong) {
        this.customerLong = customerLong;
    }

    public Integer getCustomerSectorId() {
        return customerSectorId;
    }

    public void setCustomerSectorId(Integer customerSectorId) {
        this.customerSectorId = customerSectorId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public Object getVenderAddress() {
        return venderAddress;
    }

    public void setVenderAddress(Object venderAddress) {
        this.venderAddress = venderAddress;
    }

    public Double getVenderLat() {
        return venderLat;
    }

    public void setVenderLat(Double venderLat) {
        this.venderLat = venderLat;
    }

    public Double getVenderLong() {
        return venderLong;
    }

    public void setVenderLong(Double venderLong) {
        this.venderLong = venderLong;
    }

    public String getVenderMobile() {
        return venderMobile;
    }

    public void setVenderMobile(String venderMobile) {
        this.venderMobile = venderMobile;
    }

    public Integer getVenderSector() {
        return venderSector;
    }

    public void setVenderSector(Integer venderSector) {
        this.venderSector = venderSector;
    }

}
