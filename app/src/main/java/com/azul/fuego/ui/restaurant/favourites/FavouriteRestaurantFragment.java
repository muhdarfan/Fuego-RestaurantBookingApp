package com.azul.fuego.ui.restaurant.favourites;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azul.fuego.R;

public class FavouriteRestaurantFragment extends Fragment {

    private FavouriteRestaurantViewModel mViewModel;

    public static FavouriteRestaurantFragment newInstance() {
        return new FavouriteRestaurantFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favourite_restaurant_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FavouriteRestaurantViewModel.class);
        // TODO: Use the ViewModel
    }

}