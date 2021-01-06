package com.azul.fuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText name, email, pass, confirmPass, phone;
    Button regBtn;
    TextView loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.register_et_name);
        email = findViewById(R.id.register_et_email);
        pass = findViewById(R.id.register_et_pass);
        confirmPass = findViewById(R.id.register_et_confirmpass);
        phone = findViewById(R.id.register_et_phone);

        regBtn = findViewById(R.id.register_btn_submit);
        loginBtn = findViewById(R.id.register_tv_login);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateField()) {

                }
            }
        });
        loginBtn.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)));
    }

    private Boolean ValidateField() {
        if (TextUtils.isEmpty(name.getText().toString().trim())) {
            name.setError("");
        } else if (TextUtils.isEmpty(email.getText().toString().trim())) {
            email.setError("");
        } else if (TextUtils.isEmpty(pass.getText().toString().trim())) {
            pass.setError("");
        } else if (TextUtils.isEmpty(confirmPass.getText().toString().trim())) {
            confirmPass.setError("");
        } else if (TextUtils.isEmpty(phone.getText().toString().trim())) {
            phone.setError("");
        } else {
            return true;
        }

        return false;
    }
}