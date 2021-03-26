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

public class SignupActivity extends AppCompatActivity {
    EditText etSignInName, etSignInPhone, SignInMyPassword, etConfirmPassword, buttonSignUp, createNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }


    public void OpenLoginActivity(View view) {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
    }
}