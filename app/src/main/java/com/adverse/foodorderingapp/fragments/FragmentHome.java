package com.adverse.foodorderingapp.fragments;

import android.app.FragmentManager;
import android.content.Context;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.adapter.MealCategoryAdapter;
import com.adverse.foodorderingapp.adapter.MealTypeAdapter;
import com.adverse.foodorderingapp.api.RetrofitClient;
import com.adverse.foodorderingapp.models.MealCategoryModel;
import com.adverse.foodorderingapp.models.MealCategoriesResponseModel;
import com.adverse.foodorderingapp.utils.OnRecyclerViewItemClickListener;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment implements OnRecyclerViewItemClickListener {
    //    to get Activity/Application Context
    Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    RecyclerView recyclerViewHorizontal, recyclerViewVertical;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //        getting fragment layout's recycler view where content will be displayed
        recyclerViewHorizontal = (RecyclerView) view.findViewById(R.id.home_Horizontal_recyclerView1);
        recyclerViewVertical = (RecyclerView) view.findViewById(R.id.home_vertical_recyclerView1);

//        defining layout manager to manage recycler view's items rendering from adapter'
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerViewVertical.setLayoutManager(linearLayoutManager);
        final LinearLayoutManager linearLayoutManagerHorizontal = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHorizontal.setLayoutManager(linearLayoutManagerHorizontal);

//        ImageSlider
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://asset20.ckassets.com/blog/wp-content/uploads/sites/5/2019/11/1-1.jpg", "1 Image", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://static.toiimg.com/photo/67092798.cms", "2 Image", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://media.weddingsonly.in/blogger/07e70e1ef704fbee94208ed621a62b34/8ba81_punjabifoodfeatured.jpg", "4 Image", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn0.weddingwire.in/articles/images/1/3/7/7/img_57731/r10_2x_gujarati-food-items-swiggy-lead.jpg", "4 Image", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.deputy.com/uploads/2018/10/The-Most-Popular-Menu-Items-That-You-should-Consider-Adding-to-Your-Restaurant_Content-image2-min-1024x569.png", "4 Image", ScaleTypes.FIT));

        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


//        Api request
        // TODO: 3/18/2021 : create api request response functionality here

        Call<MealCategoriesResponseModel> call = RetrofitClient.getInstance().getApi().getMealCategory();
        call.enqueue(new Callback<MealCategoriesResponseModel>() {
            @Override
            public void onResponse(Call<MealCategoriesResponseModel> call, Response<MealCategoriesResponseModel> response) {
                try {
                    if (String.valueOf(response.code()).equals("200")) {
                        Log.i("Response ", response.toString());
                        List<MealCategoryModel> mealCategoryModelList = response.body().getCategoryList();
                        if (mealCategoryModelList.size() > 0) {
                            final MealCategoryAdapter mealCategoryAdapter = new MealCategoryAdapter(mealCategoryModelList);
                            mealCategoryAdapter.setOnRecyclerViewItemClickListener(FragmentHome.this);
                            recyclerViewVertical.setAdapter(mealCategoryAdapter);

                            final MealTypeAdapter mealTypeAdapter = new MealTypeAdapter(mealCategoryModelList);
                            mealTypeAdapter.setOnRecyclerViewItemClickListener(FragmentHome.this);
                            recyclerViewHorizontal.setAdapter(mealTypeAdapter);
                            // progressDialog.dismiss();
                            // getActivity().setTitle("Top headlines");
                        } else {
                            Log.i("Response ", "Error");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
                // sharedPreferences.edit().putString("access_token", response.body().getAccessToken()).apply();
                //  startActivity(new Intent(LoginActivity.this, MainActivity.class));
                // finish();

            }

            @Override
            public void onFailure(Call<MealCategoriesResponseModel> call, Throwable t) {
                //   Toast.makeText(LoginActivity.this, "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("Error: ", t.getMessage());
            }
        });

        return view;
    }

    @Override
    public void onItemClick(int adapterPosition, View view) {
        switch (view.getId()) {
            case R.id.food_category_adapter_layout:
            case R.id.food_type_linear_layout:
                MealCategoryModel mealCategoryModel = (MealCategoryModel) view.getTag();

                if (!TextUtils.isEmpty(mealCategoryModel.getCode())) {
                    Log.e("clicked category", mealCategoryModel.getCode());

                    FragmentTransaction t = this.getFragmentManager().beginTransaction();
                    Fragment fragmentCategoryProduct = new FragmentCategoryProduct();
                    String backStackFragmentCategoryProduct = fragmentCategoryProduct.getClass().getName();
                    Bundle b = new Bundle();
                    b.putString("mealCategoryCode", mealCategoryModel.getCode());
                    b.putString("mealCategoryName", mealCategoryModel.getName());
                    fragmentCategoryProduct.setArguments(b);
                    t.replace(R.id.fragment_container, fragmentCategoryProduct).addToBackStack(backStackFragmentCategoryProduct).commit();
                }
                break;

        }
    }
}