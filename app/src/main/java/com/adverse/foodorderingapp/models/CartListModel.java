package com.adverse.foodorderingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartListModel {
    @SerializedName("ProductCode")
    @Expose
    private String productCode;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("LowPrice")
    @Expose
    private Double lowPrice;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("Photo1")
    @Expose
    private String photo1;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
}
