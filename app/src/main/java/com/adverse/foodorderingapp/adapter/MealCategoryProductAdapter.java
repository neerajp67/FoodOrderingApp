package com.adverse.foodorderingapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.models.MealCategoryProductModel;
import com.adverse.foodorderingapp.utils.GlideApp;
import com.adverse.foodorderingapp.utils.OnRecyclerViewItemClickListener;

import java.util.List;

public class MealCategoryProductAdapter extends RecyclerView.Adapter<MealCategoryProductAdapter.ViewHolder> {

    private List<MealCategoryProductModel> mealCategoryProductModels;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public MealCategoryProductAdapter( List<MealCategoryProductModel> mealCategoryProductModelList) {
        this.mealCategoryProductModels = mealCategoryProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        context = holder.itemView.getContext();
        final MealCategoryProductModel mealCategoryProductModel = mealCategoryProductModels.get(position);

        if (!TextUtils.isEmpty(mealCategoryProductModel.getPhoto1())){
            GlideApp.with(context).load(mealCategoryProductModel.getPhoto1()).into((holder.food_item_image));
        }
        if (!TextUtils.isEmpty(mealCategoryProductModel.getMealTypeCode())){
            //non-vag
            if(mealCategoryProductModel.getMealTypeCode().equals("MealType03302021130645")){
                holder.food_item_meal_type.setImageResource(R.drawable.nonveg);
            }
            //veg
             else if(mealCategoryProductModel.getMealTypeCode().equals("MealType03302021130634")){
                holder.food_item_meal_type.setImageResource(R.drawable.veg1);
            }
        }
        if (!TextUtils.isEmpty(mealCategoryProductModel.getTitle())){
           holder.food_item_title.setText(mealCategoryProductModel.getTitle());
        }
        if (!TextUtils.isEmpty(mealCategoryProductModel.getLowPrice())){
            holder.food_item_price.setText("₹" + mealCategoryProductModel.getLowPrice());
        }
        if (!TextUtils.isEmpty(mealCategoryProductModel.getHighPrice())){
            holder.food_item_max_price.setText("₹" + mealCategoryProductModel.getHighPrice());
        }
        if (!TextUtils.isEmpty(mealCategoryProductModel.getDetail())){
            holder.food_item_description.setText(mealCategoryProductModel.getDetail());
        }
        holder.food_item_adapter_layout.setTag(mealCategoryProductModel);
    }

    @Override
    public int getItemCount() {
        return mealCategoryProductModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView food_item_title, food_item_price, food_item_max_price, food_item_description;
        private ImageView food_item_image, food_item_meal_type;
        Button food_item_add_to_cart_button;
        private LinearLayout food_item_adapter_layout;
        ViewHolder(View view) {
            super(view);

            food_item_title = view.findViewById(R.id.food_item_title);
            food_item_price = view.findViewById(R.id.food_item_price);
            food_item_max_price = view.findViewById(R.id.food_item_max_price);
            food_item_description = view.findViewById(R.id.food_item_description);
            food_item_image = view.findViewById(R.id.food_item_image);
            food_item_image = view.findViewById(R.id.food_item_image);
            food_item_meal_type = view.findViewById(R.id.food_item_meal_type);
            food_item_adapter_layout = view.findViewById(R.id.food_item_adapter_layout);
            food_item_add_to_cart_button = view.findViewById(R.id.food_item_add_to_cart_button);

            food_item_add_to_cart_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                    }
                }
            });

//            food_item_adapter_layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (onRecyclerViewItemClickListener != null) {
//                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
//                    }
//                }
//            });
        }
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
