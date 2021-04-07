package com.adverse.foodorderingapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.adapter.MealCategoryAdapter;
import com.adverse.foodorderingapp.adapter.MealCategoryProductAdapter;
import com.adverse.foodorderingapp.api.RetrofitClient;
import com.adverse.foodorderingapp.models.MealCategoriesResponseModel;
import com.adverse.foodorderingapp.models.MealCategoryModel;
import com.adverse.foodorderingapp.models.MealCategoryProductModel;
import com.adverse.foodorderingapp.models.MealCategoryProductResponseModel;
import com.adverse.foodorderingapp.models.SaveToCartResponse;
import com.adverse.foodorderingapp.utils.OnRecyclerViewItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategoryProduct extends Fragment implements OnRecyclerViewItemClickListener {
    //    to get Activity/Application Context
    Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    RecyclerView food_by_category_recyclerView;
    SharedPreferences sharedPreferences;

    //    this is called when fragment is created, context need to be initialized, otherwise it may throw null exception..
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    //    this is called when fragment is created and ready to be rendered
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        getting fragment layout file to be rendered
        View view = inflater.inflate(R.layout.fragment_category_product, container, false);
        //        getting fragment layout's recycler view where content will be displayed
        food_by_category_recyclerView = (RecyclerView) view.findViewById(R.id.food_by_category_recyclerView);
        sharedPreferences = context.getSharedPreferences("com.adverse.foodorderingapp", Context.MODE_PRIVATE);

//        defining layout manager to manage recycler view's items rendering from adapter'
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        food_by_category_recyclerView.setLayoutManager(linearLayoutManager);
//
        Bundle bundle = getArguments();
        String mealCategoryCode = getArguments().getString("mealCategoryCode");
        String mealCategoryName = getArguments().getString("mealCategoryName");
        Log.e("fragment product", mealCategoryCode);

//        String category = "Categories03302021124149";
        Call<MealCategoryProductResponseModel> call = RetrofitClient.getInstance().getApi().getProductByCategory(mealCategoryCode);
        call.enqueue(new Callback<MealCategoryProductResponseModel>() {
            @Override
            public void onResponse(Call<MealCategoryProductResponseModel> call, Response<MealCategoryProductResponseModel> response) {
                try {
                    if (String.valueOf(response.code()).equals("200")) {
                        Log.i("Response ", response.toString());
                        List<MealCategoryProductModel> mealCategoryModelList = response.body().getProductList();
                        if (mealCategoryModelList.size() > 0) {
                            final MealCategoryProductAdapter mealCategoryProductAdapter = new MealCategoryProductAdapter(mealCategoryModelList);
                            mealCategoryProductAdapter.setOnRecyclerViewItemClickListener(FragmentCategoryProduct.this);
                            food_by_category_recyclerView.setAdapter(mealCategoryProductAdapter);
                            getActivity().setTitle(mealCategoryName);
                        } else {
                            Log.i("Response ", "Error");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MealCategoryProductResponseModel> call, Throwable t) {
                //   Toast.makeText(LoginActivity.this, "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("Error: ", t.getMessage());
            }
        });

        return view;

    }

    @Override
    public void onItemClick(int adapterPosition, View view) {
        switch (view.getId()) {
            case R.id.food_item_adapter_layout:
                MealCategoryProductModel mealCategoryModel = (MealCategoryProductModel) view.getTag();
                if (!TextUtils.isEmpty(mealCategoryModel.getProductTypeCod())) {
                    Log.e("clicked category", mealCategoryModel.getProductTypeCod());
                }
                String access_token = "Bearer " + sharedPreferences.getString("access_token", "");

                try {
                    JSONObject productDetail = new JSONObject();
                    productDetail.put("ProductCode", mealCategoryModel.getProductTypeCod());
                    productDetail.put("Quantity", 10);
                    Log.i("json", productDetail.toString());
                    Call<SaveToCartResponse> addToCartCall = RetrofitClient.getInstance().getApi().addToCart(access_token, productDetail);
                    addToCartCall.enqueue(new Callback<SaveToCartResponse>() {
                        @Override
                        public void onResponse(Call<SaveToCartResponse> call, Response<SaveToCartResponse> response) {
                            Log.i("Response ", response.toString());
                            Log.i("json on response", productDetail.toString());
                            Log.i("Response test", response.body().toString());
                            Log.i("errorMsg", response.body().getErrorMsg());
                            Toast.makeText(context, "errorMsg" + response.body().getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<SaveToCartResponse> call, Throwable t) {
                            Log.i("error ", "failed..");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}