package com.azul.fuego.ui.home;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azul.fuego.core.Restaurant;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Restaurant>> restaurantLiveData;
    private ArrayList<Restaurant> restaurantList;

    public HomeViewModel() {
        restaurantLiveData = new MutableLiveData<>();
        restaurantList = new ArrayList<>();

        populateList();
        restaurantLiveData.setValue(restaurantList);
    }

    private void populateList() {
        Restaurant rest = new Restaurant("McD", "+603-88615413", "39, Jalan Tuanku Abdul Rahman, Presint 2, 62100 Putrajaya, WP", "Classic, long running fast-food",
                3.4, Uri.parse("https://google.com"), Uri.parse("https://google.com"));
        restaurantList.add(rest);
        restaurantList.add(rest);
        restaurantList.add(rest);
        restaurantList.add(rest);
        restaurantList.add(rest);
        restaurantList.add(rest);
        restaurantList.add(rest);
        restaurantList.add(rest);
    }
    public LiveData<ArrayList<Restaurant>> getRestaurantMutableLiveData() {
        return restaurantLiveData;
    }
}