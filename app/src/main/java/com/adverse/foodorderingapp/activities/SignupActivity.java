package com.adverse.foodorderingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adverse.foodorderingapp.R;

public class SignupActivity extends AppCompatActivity {
    EditText etSignInName, etSignInPhone, SignInMyPassword, etConfirmPassword, buttonSignUp, createNewAccount;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.adverse.foodorderingapp", Context.MODE_PRIVATE);
        String testToken = sharedPreferences.getString("access_token", "default token");
        Log.i("token text", testToken);
        Toast.makeText(SignupActivity.this, testToken, Toast.LENGTH_SHORT).show();

    }


    public void OpenLoginActivity(View view) {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
    }
}