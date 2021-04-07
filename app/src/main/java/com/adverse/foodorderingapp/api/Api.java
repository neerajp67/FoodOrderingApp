package com.adverse.foodorderingapp.api;

import com.adverse.foodorderingapp.models.CartListResponseModel;
import com.adverse.foodorderingapp.models.LoginResponse;
import com.adverse.foodorderingapp.models.MealCategoriesResponseModel;
import com.adverse.foodorderingapp.models.MealCategoryProductResponseModel;
import com.adverse.foodorderingapp.models.SaveToCartResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("token")
    Call<LoginResponse> loginUser(@Field("username") String username,
                                  @Field("password") String password,
                                  @Field("grant_type") String grant_type);

    @GET("api/meals/mealCategories")
    Call<MealCategoriesResponseModel> getMealCategory();

    @POST("api/meals/product")
    Call<MealCategoryProductResponseModel> getProductByCategory(@Query("category") String category);

    @POST("api/meals/SaveToCart")
    Call<SaveToCartResponse> addToCart(@Header("Authorization") String token, @Body JSONObject productDetail);

    @POST("api/Meals/CartList")
    Call<CartListResponseModel> getCartList(@Header("Authorization") String token);
}

