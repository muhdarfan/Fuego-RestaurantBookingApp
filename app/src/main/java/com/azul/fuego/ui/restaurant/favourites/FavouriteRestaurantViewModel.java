package com.azul.fuego.ui.restaurant.favourites;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azul.fuego.core.Fuego;
import com.azul.fuego.core.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FavouriteRestaurantViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Restaurant>> favouriteListData;

    public FavouriteRestaurantViewModel() {
        favouriteListData = new MutableLiveData<>();
        ArrayList<Restaurant> restaurantsList = new ArrayList<>();

        Fuego.mStore.collection("restaurants").whereIn("refID", Fuego.UserData.getFavourites()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (DocumentSnapshot snapshot : task.getResult()) {
                        restaurantsList.add(snapshot.toObject(Restaurant.class));
                        favouriteListData.setValue(restaurantsList);
                    }
                }
            }
        });
    }

    public MutableLiveData<ArrayList<Restaurant>> getFavouriteListData() {
        return favouriteListData;
    }
}