
package com.smartgrocerydelivery.Model.Itemdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameter {

    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("mrp")
    @Expose
    private Integer mrp;
    @SerializedName("sellingPrice")
    @Expose
    private Float sellingPrice;
    @SerializedName("totalMRP")
    @Expose
    private Integer totalMRP;
    @SerializedName("totalSellingPrice")
    @Expose
    private Float totalSellingPrice;
    @SerializedName("groceryContentMasterId")
    @Expose
    private Integer groceryContentMasterId;
    @SerializedName("categoryMasterId")
    @Expose
    private Integer categoryMasterId;
    @SerializedName("subCategoryMasterId")
    @Expose
    private Integer subCategoryMasterId;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("orderDescriptionId")
    @Expose
    private Integer orderDescriptionId;
    @SerializedName("rowStatus")
    @Expose
    private Integer rowStatus;
    @SerializedName("oldQuantity")
    @Expose
    private Integer oldQuantity;
    @SerializedName("mappingId")
    @Expose
    private Integer mappingId;
    @SerializedName("adminRefund")
    @Expose
    private Boolean adminRefund;
    @SerializedName("promoCode")
    @Expose
    private String promoCode;
    @SerializedName("promoCodeAmount")
    @Expose
    private Integer promoCodeAmount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getTotalMRP() {
        return totalMRP;
    }

    public void setTotalMRP(Integer totalMRP) {
        this.totalMRP = totalMRP;
    }

    public Float getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(Float totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }

    public Integer getGroceryContentMasterId() {
        return groceryContentMasterId;
    }

    public void setGroceryContentMasterId(Integer groceryContentMasterId) {
        this.groceryContentMasterId = groceryContentMasterId;
    }

    public Integer getCategoryMasterId() {
        return categoryMasterId;
    }

    public void setCategoryMasterId(Integer categoryMasterId) {
        this.categoryMasterId = categoryMasterId;
    }

    public Integer getSubCategoryMasterId() {
        return subCategoryMasterId;
    }

    public void setSubCategoryMasterId(Integer subCategoryMasterId) {
        this.subCategoryMasterId = subCategoryMasterId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getOrderDescriptionId() {
        return orderDescriptionId;
    }

    public void setOrderDescriptionId(Integer orderDescriptionId) {
        this.orderDescriptionId = orderDescriptionId;
    }

    public Integer getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(Integer rowStatus) {
        this.rowStatus = rowStatus;
    }

    public Integer getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(Integer oldQuantity) {
        this.oldQuantity = oldQuantity;
    }

    public Integer getMappingId() {
        return mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }

    public Boolean getAdminRefund() {
        return adminRefund;
    }

    public void setAdminRefund(Boolean adminRefund) {
        this.adminRefund = adminRefund;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Integer getPromoCodeAmount() {
        return promoCodeAmount;
    }

    public void setPromoCodeAmount(Integer promoCodeAmount) {
        this.promoCodeAmount = promoCodeAmount;
    }

}
