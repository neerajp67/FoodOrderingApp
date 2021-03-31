package com.adverse.foodorderingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealCategoryProductModel {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Photo1")
    @Expose
    private String photo1;
    @SerializedName("Photo2")
    @Expose
    private String photo2;
    @SerializedName("Photo3")
    @Expose
    private String photo3;
    @SerializedName("Photo4")
    @Expose
    private String photo4;
    @SerializedName("Photo5")
    @Expose
    private String photo5;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("HighPrice")
    @Expose
    private String highPrice;
    @SerializedName("LowPrice")
    @Expose
    private String lowPrice;
    @SerializedName("MealTypeCode")
    @Expose
    private String mealTypeCode;
    @SerializedName("ProductTypeCod")
    @Expose
    private String productTypeCod;
    @SerializedName("MealCategoryCode")
    @Expose
    private String mealCategoryCode;
    @SerializedName("Detail")
    @Expose
    private String detail;
    @SerializedName("Add1")
    @Expose
    private String add1;
    @SerializedName("Add2")
    @Expose
    private String add2;
    @SerializedName("Add3")
    @Expose
    private String add3;
    @SerializedName("Add4")
    @Expose
    private String add4;
    @SerializedName("Add5")
    @Expose
    private String add5;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getPhoto5() {
        return photo5;
    }

    public void setPhoto5(String photo5) {
        this.photo5 = photo5;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getMealTypeCode() {
        return mealTypeCode;
    }

    public void setMealTypeCode(String mealTypeCode) {
        this.mealTypeCode = mealTypeCode;
    }

    public String getProductTypeCod() {
        return productTypeCod;
    }

    public void setProductTypeCod(String productTypeCod) {
        this.productTypeCod = productTypeCod;
    }

    public String getMealCategoryCode() {
        return mealCategoryCode;
    }

    public void setMealCategoryCode(String mealCategoryCode) {
        this.mealCategoryCode = mealCategoryCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Object getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public Object getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public Object getAdd3() {
        return add3;
    }

    public void setAdd3(String add3) {
        this.add3 = add3;
    }

    public Object getAdd4() {
        return add4;
    }

    public void setAdd4(String add4) {
        this.add4 = add4;
    }

    public Object getAdd5() {
        return add5;
    }

    public void setAdd5(String add5) {
        this.add5 = add5;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}
