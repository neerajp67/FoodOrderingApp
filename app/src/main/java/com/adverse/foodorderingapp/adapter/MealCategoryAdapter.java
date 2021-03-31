package com.adverse.foodorderingapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.models.CategoryResponseModel;
import com.adverse.foodorderingapp.utils.GlideApp;
import com.adverse.foodorderingapp.utils.OnRecyclerViewItemClickListener;

import java.util.List;

public class MealCategoryAdapter extends RecyclerView.Adapter<MealCategoryAdapter.ViewHolder> {

    private List<CategoryResponseModel> categoryResponseModelList;
    //    private Context context;
  
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public MealCategoryAdapter( List<CategoryResponseModel> categoryResponseModelList) {
        this.categoryResponseModelList = categoryResponseModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_category_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        context = holder.itemView.getContext();
        final CategoryResponseModel categoryResponseModel = categoryResponseModelList.get(position);
        if (!TextUtils.isEmpty(categoryResponseModel.getName())){
            holder.food_category_title.setText(categoryResponseModel.getName());
        }
        if (!TextUtils.isEmpty(categoryResponseModel.getImages())){
            GlideApp.with(context).load(categoryResponseModel.getImages()).into(holder.food_category_image);
        } else{
            holder.food_category_image.setImageResource(R.drawable.aalu);
        }
        holder.food_category_adapter_layout.setTag(categoryResponseModel);

    }

    @Override
    public int getItemCount() {
        return categoryResponseModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView food_category_title, food_category_starting_price, food_category_description;
        private ImageView food_category_image;
        private LinearLayout food_category_adapter_layout;
        ViewHolder(View view) {
            super(view);

            food_category_title = view.findViewById(R.id.food_category_title);
            food_category_starting_price = view.findViewById(R.id.food_category_starting_price);
            food_category_description = view.findViewById(R.id.food_category_description);
            food_category_image = view.findViewById(R.id.food_category_image);
            food_category_adapter_layout = view.findViewById(R.id.food_category_adapter_layout);

            food_category_adapter_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                    }
                }
            });
        }
    }
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

}
