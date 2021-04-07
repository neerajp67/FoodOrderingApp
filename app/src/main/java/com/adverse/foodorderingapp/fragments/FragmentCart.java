package com.adverse.foodorderingapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.adapter.CartListAdapter;
import com.adverse.foodorderingapp.adapter.MealCategoryProductAdapter;
import com.adverse.foodorderingapp.api.RetrofitClient;
import com.adverse.foodorderingapp.models.CartListModel;
import com.adverse.foodorderingapp.models.CartListResponseModel;
import com.adverse.foodorderingapp.models.MealCategoryModel;
import com.adverse.foodorderingapp.models.MealCategoryProductModel;
import com.adverse.foodorderingapp.models.MealCategoryProductResponseModel;
import com.adverse.foodorderingapp.utils.OnRecyclerViewItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCart extends Fragment implements OnRecyclerViewItemClickListener{
    //    to get Activity/Application Context
    Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    RecyclerView cart_recyclerView;
    Button proceed_to_pay_button;
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        sharedPreferences = context.getSharedPreferences("com.adverse.foodorderingapp",Context.MODE_PRIVATE);

        //        getting fragment layout's recycler view where content will be displayed
        cart_recyclerView = (RecyclerView) view.findViewById(R.id.cart_recyclerView);
        proceed_to_pay_button = view.findViewById(R.id.proceed_to_pay_button);

        proceed_to_pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "only COD is available!", Toast.LENGTH_LONG).show();
            }
        });

//        defining layout manager to manage recycler view's items rendering from adapter'
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        cart_recyclerView.setLayoutManager(linearLayoutManager);
//
//        Bundle bundle = getArguments();
//        String mealCategoryCode = "Categories03302021124149";;
//        String mealCategoryName = getArguments().getString("mealCategoryName");
//        Log.e("fragment product", mealCategoryCode);

//        String category = "Categories03302021124149";
        String access_token = "Bearer " + sharedPreferences.getString("access_token", "");
        Log.i("access_token ", access_token);
        Call<CartListResponseModel> call = RetrofitClient.getInstance().getApi().getCartList(access_token);
        call.enqueue(new Callback<CartListResponseModel>() {
            @Override
            public void onResponse(Call<CartListResponseModel> call, Response<CartListResponseModel> response) {
                try {
                    if (String.valueOf(response.code()).equals("200")) {
                        Log.i("Response ", response.toString());
                        List<CartListModel> cartListModelList = response.body().getCartList();
                        if (cartListModelList.size() > 0) {
                            final CartListAdapter cartListAdapter = new CartListAdapter(cartListModelList);
                            cartListAdapter.setOnRecyclerViewItemClickListener(FragmentCart.this);
                            cart_recyclerView.setAdapter(cartListAdapter);
//                            getActivity().setTitle(mealCategoryName);
                            Log.i("Response ", "Success");
                        } else {
                            Log.i("Response ", "Error");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("Error: ", e.toString());
                    Toast.makeText(context, "Something went wrong!" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<CartListResponseModel> call, Throwable t) {
                   Toast.makeText(context, "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("Error: ", t.getMessage());
            }
        });
//
        return view;

    }

    @Override
    public void onItemClick(int adapterPosition, View view) {
        switch (view.getId()) {
            case R.id.cart_item_adapter_layout:
                CartListModel cartListModel = (CartListModel) view.getTag();
                if (!TextUtils.isEmpty(cartListModel.getTitle())) {
                    Log.e("clicked category", cartListModel.getTitle());
                    Toast.makeText(context, "Selected :", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}