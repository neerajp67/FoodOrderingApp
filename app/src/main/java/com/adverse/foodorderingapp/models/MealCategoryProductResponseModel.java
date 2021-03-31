package com.adverse.foodorderingapp.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealCategoryProductResponseModel {
    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;
    @SerializedName("errorStatus")
    @Expose
    private Boolean errorStatus;
    @SerializedName("ProductList")
    @Expose
    private List<MealCategoryProductModel> productList = null;

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

    public List<MealCategoryProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<MealCategoryProductModel> productList) {
        this.productList = productList;
    }

}

