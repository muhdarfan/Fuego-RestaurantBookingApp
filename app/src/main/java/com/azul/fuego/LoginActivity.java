package com.azul.fuego;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText etUser, etPass;
    Button loginBtn;
    TextView signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.user_login_username);
        etPass = findViewById(R.id.user_login_password);
        loginBtn = findViewById(R.id.user_login_btn_signin);
        signUpBtn = findViewById(R.id.user_login_register_btn);
        signUpBtn.setText(Html.fromHtml(getString(R.string.signuptext)));

        loginBtn.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(etUser.getText()) || !TextUtils.isEmpty(etPass.getText())) {

            } else {
                if (etUser.getText().toString().isEmpty()) {
                    etUser.setError("");
                } else if (etPass.getText().toString().isEmpty()) {
                    etPass.setError("");
                }
            }
        });

        signUpBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}