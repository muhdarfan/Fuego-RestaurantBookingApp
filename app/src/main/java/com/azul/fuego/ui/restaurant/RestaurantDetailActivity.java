package com.azul.fuego.ui.restaurant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.azul.fuego.R;
import com.azul.fuego.core.Restaurant;
import com.azul.fuego.ui.profile.ProfileEditFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class RestaurantDetailActivity extends Fragment {
    private Restaurant restaurant;
    private TextView name, operating_hours, address, about;
    private MapView location;
    private ImageView picture;
    private Button bookBtn;
    private ImageButton backBtn;

    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurant = getArguments().getParcelable("restaurant");

            if (restaurant != null) return;
        }

        getActivity().onBackPressed();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_restaurant_detail_activity, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize
        name = view.findViewById(R.id.restaurant_detail_tv_name);
        operating_hours = view.findViewById(R.id.restaurant_detail_tv_operating_hours);
        address = view.findViewById(R.id.restaurant_detail_tv_address);
        about = view.findViewById(R.id.restaurant_detail_tv_about);
        location = view.findViewById(R.id.restaurant_detail_mv_location);
        picture = view.findViewById(R.id.restaurant_detail_iv_image);
        bookBtn = view.findViewById(R.id.restaurant_detail_btn_book);
        backBtn = view.findViewById(R.id.restaurant_detail_btn_back);

        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        about.setText(restaurant.getAbout());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RestaurantDetailActivity.this).popBackStack();
            }
        });

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}