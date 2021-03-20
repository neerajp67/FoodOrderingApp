package com.adverse.foodorderingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adverse.foodorderingapp.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    //    to get Activity/Application Context
    Context context;

    RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.home_Horizontal_recyclerView1);

//        defining layout manager to manage recycler view's items rendering from adapter'
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

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

        return view;
    }
}