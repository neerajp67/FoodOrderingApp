package com.adverse.foodorderingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveToCartResponse {

    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;
    @SerializedName("errorStatus")
    @Expose
    private Boolean errorStatus;

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
}
