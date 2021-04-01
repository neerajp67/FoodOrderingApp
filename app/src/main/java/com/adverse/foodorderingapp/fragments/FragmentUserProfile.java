package com.adverse.foodorderingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.adverse.foodorderingapp.R;

public class FragmentUserProfile  extends Fragment {
    ImageView edit_user_image_view_button, user_profile_picture;
    TextView user_name,user_mobile_number, user_email, user_gender;
    Button logout_button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        Context context = getContext();
        edit_user_image_view_button = view.findViewById(R.id.edit_user_image_view_button);
        user_profile_picture = view.findViewById(R.id.user_profile_picture);
        user_name = view.findViewById(R.id.user_name);
        user_mobile_number = view.findViewById(R.id.user_mobile_number);
        user_email = view.findViewById(R.id.user_email);
        user_gender = view.findViewById(R.id.user_gender);
        logout_button = view.findViewById(R.id.logout_button);

        user_name.setText("Neeraj Pandey");
                user_mobile_number.setText("+91 " + "9012218681");
        user_email.setText("neerajpandey025@gmail.com");
                user_gender.setText("Male" + " 24+");

        edit_user_image_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
