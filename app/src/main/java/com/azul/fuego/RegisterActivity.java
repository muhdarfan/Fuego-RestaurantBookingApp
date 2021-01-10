package com.azul.fuego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.azul.fuego.core.Fuego;
import com.azul.fuego.core.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

public class RegisterActivity extends AppCompatActivity {
    protected EditText etName, etEmail, etPass, etConfirmPass, etPhone;
    protected Button regBtn;
    protected TextView loginBtn;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.register_et_name);
        etEmail = findViewById(R.id.register_et_email);
        etPass = findViewById(R.id.register_et_pass);
        etConfirmPass = findViewById(R.id.register_et_confirmpass);
        etPhone = findViewById(R.id.register_et_phone);

        regBtn = findViewById(R.id.register_btn_submit);
        loginBtn = findViewById(R.id.register_tv_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateField()) {
                    String email = etEmail.getText().toString().trim();
                    String pass = etPass.getText().toString().trim();
                    String name = etName.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();

                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Fuego.mStore.collection("users").document(mFirebaseAuth.getUid()).set(new Users(mFirebaseAuth.getUid(), name, email, phone)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(RegisterActivity.this, MainMenuActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Failed to create user. Contact administrator.", Toast.LENGTH_LONG).show();
                                            mFirebaseAuth.getCurrentUser().delete();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(RegisterActivity.this, String.format("An error has been occurred. [MSG: %s]", task.getException().getMessage()), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        loginBtn.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)));
    }

    private Boolean ValidateField() {
        if (TextUtils.isEmpty(etName.getText().toString().trim())) {
            etName.setError("");
        } else if (TextUtils.isEmpty(etEmail.getText().toString().trim())) {
            etEmail.setError("");
        } else if (TextUtils.isEmpty(etPass.getText().toString().trim())) {
            etPass.setError("");
        } else if (TextUtils.isEmpty(etConfirmPass.getText().toString().trim())) {
            etConfirmPass.setError("");
        } else if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            etPhone.setError("");
        } else if (!etPass.getText().toString().equals(etConfirmPass.getText().toString().trim())) {
            etPass.setError("Password is mismatch.");
        } else {
            return true;
        }

        return false;
    }
}