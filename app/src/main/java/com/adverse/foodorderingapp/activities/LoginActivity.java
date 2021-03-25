package com.adverse.foodorderingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.api.RetrofitClient;
import com.adverse.foodorderingapp.models.LoginResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etMobile, etpass;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.btnlogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "9874563210";
                String password = "123";
                String grant_type = "password";

                Call<LoginResponse> call = RetrofitClient.getInstance().getApi().loginUser(username, password, grant_type);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        // LoginResponse loginResponse = new LoginResponse();
                        Log.i("loginResponse", response.body().getAccessToken());
                        Toast.makeText(LoginActivity.this, "Access Token: " + response.body().getAccessToken(),
                                Toast.LENGTH_LONG).show();

//                        Log.i("Response access token", s + loginResponse.getAccessToken());
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("Error: ", t.getMessage());
                    }
                });

            }
        });
    }

    public void SignUp(View view) {
        //startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}