package com.azul.fuego.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azul.fuego.MainMenuActivity;
import com.azul.fuego.R;
import com.azul.fuego.core.Restaurant;
import com.azul.fuego.core.RestaurantRecyclerViewAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    RecyclerView mainRecyclerView;
    RestaurantRecyclerViewAdapter restaurantAdapter;
    EditText etSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSearch = view.findViewById(R.id.home_et_search);
        mainRecyclerView = view.findViewById(R.id.home_rv_restaurant);

        homeViewModel.getRestaurantMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Restaurant>>() {
            @Override
            public void onChanged(ArrayList<Restaurant> restaurants) {
                restaurantAdapter = new RestaurantRecyclerViewAdapter(getContext(), restaurants);
                mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mainRecyclerView.setAdapter(restaurantAdapter);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = etSearch.getText().toString().trim();
                restaurantAdapter.getFilter().filter(query);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}