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
import com.adverse.foodorderingapp.models.MealCategoryModel;
import com.adverse.foodorderingapp.utils.GlideApp;
import com.adverse.foodorderingapp.utils.OnRecyclerViewItemClickListener;

import java.util.List;

public class MealTypeAdapter extends RecyclerView.Adapter<MealTypeAdapter.ViewHolder> {

    private List<MealCategoryModel> mealCategoryModelList;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public MealTypeAdapter( List<MealCategoryModel> mealCategoryModelList) {
        this.mealCategoryModelList = mealCategoryModelList;
    }

    @NonNull
    @Override
    public MealTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_type_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealTypeAdapter.ViewHolder holder, int position) {
        context = holder.itemView.getContext();
        final MealCategoryModel mealCategoryModel = mealCategoryModelList.get(position);
        if (!TextUtils.isEmpty(mealCategoryModel.getName())){
            holder.food_type_title.setText(mealCategoryModel.getName());
        }
        if (!TextUtils.isEmpty(mealCategoryModel.getImages())){
            GlideApp.with(context).load(mealCategoryModel.getImages()).into(holder.food_type_image);
        } else{
            holder.food_type_image.setImageResource(R.drawable.aalu);
        }
        holder.food_type_linear_layout.setTag(mealCategoryModel);
    }

    @Override
    public int getItemCount() {
        return mealCategoryModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView food_type_title;
        private ImageView food_type_image;
        private LinearLayout food_type_linear_layout;
        ViewHolder(View view) {
            super(view);

            food_type_title = view.findViewById(R.id.food_type_title);
            food_type_image = view.findViewById(R.id.food_type_image);
            food_type_linear_layout = view.findViewById(R.id.food_type_linear_layout);

            food_type_linear_layout.setOnClickListener(new View.OnClickListener() {
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
