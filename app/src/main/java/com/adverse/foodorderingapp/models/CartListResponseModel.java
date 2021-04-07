package com.adverse.foodorderingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartListResponseModel {
    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;
    @SerializedName("errorStatus")
    @Expose
    private Boolean errorStatus;
    @SerializedName("cartList")
    @Expose
    private List<CartListModel> cartList = null;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Boolean getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(Boolean errorStatus) {
        this.errorStatus = errorStatus;
    }

    public List<CartListModel> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartListModel> cartList) {
        this.cartList = cartList;
    }
}
