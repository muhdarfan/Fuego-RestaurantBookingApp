package com.azul.fuego.core;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/*
    ########################
    # FUEGO Bootstrap Class
    # @author Arfan
    # @date Jan 2021
    ########################

 */
public class Fuego {
    public static FirebaseAuth mAuth;
    public static FirebaseUser User;
    public static FirebaseFirestore mStore;
    public static StorageReference mStorage;
    public static Users UserData;
    public static String[] availableLanguage = {"English", "Bahasa Melayu", "Chinese"};

    public Fuego() {
        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();
        mStore = FirebaseFirestore.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    public static void SignOut() {
        mAuth.signOut();
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    static String GenerateRandomString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
