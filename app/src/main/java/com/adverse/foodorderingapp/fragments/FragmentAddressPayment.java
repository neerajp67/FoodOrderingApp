package com.adverse.foodorderingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.utils.OnRecyclerViewItemClickListener;

public class FragmentAddressPayment extends Fragment implements OnRecyclerViewItemClickListener {

    Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    RecyclerView cart_recyclerView;

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
        View view = inflater.inflate(R.layout.fragment_address_final_payment, container, false);
        //        getting fragment layout's recycler view where content will be displayed
        // cart_recyclerView = (RecyclerView) view.findViewById(R.id.cart_recyclerView);
        return view;
    }

    @Override
    public void onItemClick(int adapterPosition, View view) {

    }
}
