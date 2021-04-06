package com.adverse.foodorderingapp.fragments;

import android.content.Context;
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
import com.adverse.foodorderingapp.adapter.MealCategoryProductAdapter;
import com.adverse.foodorderingapp.api.RetrofitClient;
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
        //        getting fragment layout's recycler view where content will be displayed
       // cart_recyclerView = (RecyclerView) view.findViewById(R.id.cart_recyclerView);
        proceed_to_pay_button = view.findViewById(R.id.proceed_to_pay_button);

        proceed_to_pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "only COD is available!", Toast.LENGTH_LONG).show();
            }
        });

//        defining layout manager to manage recycler view's items rendering from adapter'
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        cart_recyclerView.setLayoutManager(linearLayoutManager);
//
//        Bundle bundle = getArguments();
//        String mealCategoryCode = "Categories03302021124149";;
//        String mealCategoryName = getArguments().getString("mealCategoryName");
//        Log.e("fragment product", mealCategoryCode);

//        String category = "Categories03302021124149";
//        Call<MealCategoryProductResponseModel> call = RetrofitClient.getInstance().getApi().getProductByCategory(mealCategoryCode);
//        call.enqueue(new Callback<MealCategoryProductResponseModel>() {
//            @Override
//            public void onResponse(Call<MealCategoryProductResponseModel> call, Response<MealCategoryProductResponseModel> response) {
//                try {
//                    if (String.valueOf(response.code()).equals("200")) {
//                        Log.i("Response ", response.toString());
//                        List<MealCategoryProductModel> mealCategoryModelList = response.body().getProductList();
//                        if (mealCategoryModelList.size() > 0) {
////                            final MealCategoryProductAdapter mealCategoryProductAdapter = new MealCategoryProductAdapter(mealCategoryModelList);
////                            mealCategoryProductAdapter.setOnRecyclerViewItemClickListener(FragmentCart.this);
////                            cart_recyclerView.setAdapter(mealCategoryProductAdapter);
////                            getActivity().setTitle(mealCategoryName);
//                            Log.i("Response ", "Success");
//                        } else {
//                            Log.i("Response ", "Error");
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MealCategoryProductResponseModel> call, Throwable t) {
//                //   Toast.makeText(LoginActivity.this, "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
//                Log.i("Error: ", t.getMessage());
//            }
//        });
//
        return view;

    }

    @Override
    public void onItemClick(int adapterPosition, View view) {
        switch (view.getId()) {
            case R.id.cart_item_adapter_layout:
                MealCategoryModel mealCategoryModel = (MealCategoryModel) view.getTag();
                if (!TextUtils.isEmpty(mealCategoryModel.getName())) {
                    Log.e("clicked category", mealCategoryModel.getName());
//                    Toast.makeText(context, "Selected :" + mealCategoryModel.getName(), Toast.LENGTH_SHORT).show();
//                    Bundle b = new Bundle();
                }
                break;
        }
    }

}