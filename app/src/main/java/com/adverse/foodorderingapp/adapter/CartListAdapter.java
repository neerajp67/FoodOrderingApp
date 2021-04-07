package com.adverse.foodorderingapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.models.CartListModel;
import com.adverse.foodorderingapp.models.MealCategoryModel;
import com.adverse.foodorderingapp.utils.GlideApp;
import com.adverse.foodorderingapp.utils.OnRecyclerViewItemClickListener;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>  {

    private List<CartListModel> cartListModelList;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public CartListAdapter( List<CartListModel> cartListModelList) {
        this.cartListModelList = cartListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        context = holder.itemView.getContext();
        final CartListModel cartListModel = cartListModelList.get(position);
        if (!TextUtils.isEmpty(cartListModel.getTitle())){
            holder.cart_item_title.setText(cartListModel.getTitle());
        }
        if (!TextUtils.isEmpty(cartListModel.getTitle())){
            holder.cart_item_price.setText(cartListModel.getLowPrice().toString());
        }
        if (!TextUtils.isEmpty(cartListModel.getTitle())){
            holder.cart_item_quantity.setText(cartListModel.getQuantity().toString());
        }
        if (!TextUtils.isEmpty(cartListModel.getPhoto1())){
            GlideApp.with(context).load(cartListModel.getPhoto1()).into(holder.cart_item_image);
        } else{
            holder.cart_item_image.setImageResource(R.drawable.aalu);
        }
        holder.cart_item_adapter_layout.setTag(cartListModel);
    }

    @Override
    public int getItemCount() {
        return cartListModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cart_item_image;
        private TextView cart_item_title, cart_item_price, cart_item_quantity;
        private ImageButton cart_item_remove_quantity, cart_item_add_quantity;
        private LinearLayout cart_item_adapter_layout;

        ViewHolder(@NonNull View view) {
            super(view);
            cart_item_image = view.findViewById(R.id.cart_item_image);
            cart_item_title = view.findViewById(R.id.cart_item_title);
            cart_item_price = view.findViewById(R.id.cart_item_price);
            cart_item_quantity = view.findViewById(R.id.cart_item_quantity);
            cart_item_remove_quantity = view.findViewById(R.id.cart_item_remove_quantity);
            cart_item_add_quantity = view.findViewById(R.id.cart_item_add_quantity);
            cart_item_adapter_layout = view.findViewById(R.id.cart_item_adapter_layout);

            cart_item_add_quantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
