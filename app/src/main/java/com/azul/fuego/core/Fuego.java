package com.azul.fuego.core;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Fuego {
    public static FirebaseAuth mAuth;
    public static FirebaseUser User;
    public static Users UserData;
    public static String[] availableLanguage = {"English", "Bahasa Melayu", "Chinese"};

    public Fuego() {
        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();
    }

    public static boolean isLoggedIn() {
        return (User != null);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
