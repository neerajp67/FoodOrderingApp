package com.adverse.foodorderingapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
import com.adverse.foodorderingapp.api.RetrofitClient;
import com.adverse.foodorderingapp.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etLoginMobileNumber, etPassword;
    TextView forgetPassword;
    Button buttonLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = findViewById(R.id.buttonLogin);
        etLoginMobileNumber = findViewById(R.id.etLoginMobileNumber);
        etPassword = findViewById(R.id.etPassword);
        forgetPassword = findViewById(R.id.forgetPassword);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.adverse.foodorderingapp", Context.MODE_PRIVATE);
        String accessToken =  sharedPreferences.getString("access_token","");;
        if (!accessToken.equals("") || !accessToken.isEmpty()) {
            Log.i("Access Token", accessToken);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for demo
                String username = "admin";
                String password = "admin";

//                if (TextUtils.isEmpty(username)){
//                    etLoginMobileNumber.setError("Please enter a valid username");
//                    return;
//                }
//                if (TextUtils.isEmpty(password)){
//                    etPassword.setError("Please enter your password");
//                    return;
//                }
//                if (password.length()<6){
//                    etPassword.setError("Invalid Password!");
//                    return;
//                }

                //actual code
//                String username = etLoginMobileNumber.getText().toString();
//                String password = etPassword.getText().toString();

                String grant_type = "password";

                Call<LoginResponse> call = RetrofitClient.getInstance().getApi().loginUser(username, password, grant_type);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if (String.valueOf(response.code()).equals("200")) {
                            Log.i("Response ", response.toString());
                            Log.i("loginResponse", response.body().getAccessToken());

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            if (!TextUtils.isEmpty(response.body().getAccessToken())){
                                editor.putString("access_token", response.body().getAccessToken());
                                editor.putString("token_type", response.body().getTokenType());
                                editor.commit();
                                Log.i("Access Token saved", sharedPreferences.getString("access_token",""));
                            }
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                            //sharedPreferences.edit().putString("access_token", response.body().getAccessToken()).apply();
                        } else {
                            Log.i("Response ", "Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("Error: ", t.getMessage());
                    }
                });
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetPhone = new EditText(LoginActivity.this);
                AlertDialog.Builder resetPassword = new AlertDialog.Builder(LoginActivity.this);

                resetPassword.setTitle("Forget Password!")
                        .setMessage("Please enter your login phone number to reset the password..")
                        .setView(resetPhone)
                        .setCancelable(false)
                        .setPositiveButton("Reset Password", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String userPhone = resetPhone.getText().toString();
                                if (TextUtils.isEmpty(userPhone)) {
                                    resetPhone.setError("Please enter your registered mobile number");
                                    Toast.makeText(LoginActivity.this, "Please enter your registered mobile number!",
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Toast.makeText(LoginActivity.this, "reset test", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                resetPassword.create().show();
            }
        });
    }

    public void SignUp(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}