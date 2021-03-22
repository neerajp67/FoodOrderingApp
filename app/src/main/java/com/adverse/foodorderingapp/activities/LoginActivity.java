package com.adverse.foodorderingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.adverse.foodorderingapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText etMobile,etpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void SignUp(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}