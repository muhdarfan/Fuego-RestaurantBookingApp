package com.azul.fuego.ui.restaurant.favourites;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azul.fuego.R;
import com.azul.fuego.core.FavouriteRestaurantRecyclerViewAdapter;
import com.azul.fuego.core.Restaurant;

import java.util.ArrayList;

public class FavouriteRestaurantFragment extends Fragment {
    private FavouriteRestaurantViewModel mViewModel;
    private RecyclerView favouriteRecyclerView;
    private FavouriteRestaurantRecyclerViewAdapter favouriteAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(FavouriteRestaurantViewModel.class);

        return inflater.inflate(R.layout.favourite_restaurant_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favouriteRecyclerView = view.findViewById(R.id.favourite_rv_restaurant_list);
        mViewModel.getFavouriteListData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Restaurant>>() {
            @Override
            public void onChanged(ArrayList<Restaurant> restaurants) {
                if (restaurants != null) {
                    favouriteAdapter = new FavouriteRestaurantRecyclerViewAdapter(getContext(), restaurants);
                    favouriteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    favouriteRecyclerView.setAdapter(favouriteAdapter);
                }
            }
        });
    }
}