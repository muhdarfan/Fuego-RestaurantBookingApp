package com.azul.fuego.ui.profile;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azul.fuego.core.Fuego;
import com.azul.fuego.core.Restaurant;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<FirebaseUser> userData;

    public ProfileViewModel() {
        userData = new MutableLiveData<>();
        userData.setValue(Fuego.User);
    }

    public LiveData<FirebaseUser> getUserDataMutableLiveData() {
        return userData;
    }
}