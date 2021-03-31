package com.adverse.foodorderingapp.api;

import com.adverse.foodorderingapp.models.LoginResponse;
import com.adverse.foodorderingapp.models.MealCategoriesResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("token")
    Call<LoginResponse> loginUser(@Field("username") String username,
                                  @Field("password") String password,
                                  @Field("grant_type") String grant_type);

    @GET("api/meals/mealcategories")
    Call<MealCategoriesResponseModel> getMealCategory();
}

