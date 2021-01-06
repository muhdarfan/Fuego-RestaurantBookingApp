package com.azul.fuego;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    protected EditText etUser, etPass;
    protected Button loginBtn;
    protected TextView signUpBtn;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        etUser = findViewById(R.id.user_login_username);
        etPass = findViewById(R.id.user_login_password);
        loginBtn = findViewById(R.id.user_login_btn_signin);
        signUpBtn = findViewById(R.id.user_login_register_btn);
        signUpBtn.setText(Html.fromHtml(getString(R.string.signuptext)));

        loginBtn.setOnClickListener(v -> {
            String user = etUser.getText().toString().trim();
            String pass = etPass.getText().toString().trim();

            if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
                mFirebaseAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MainMenuActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        } else {
                            Toast.makeText(LoginActivity.this, String.format("An error has been occurred. [MSG: %s]", task.getException().getMessage()), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                if (TextUtils.isEmpty(user))
                    etUser.setError("");
                else if (TextUtils.isEmpty(pass))
                    etPass.setError("");
            }
        });

        signUpBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}