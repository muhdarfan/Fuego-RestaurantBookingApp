package com.azul.fuego.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azul.fuego.R;

import java.util.List;

public class FavouriteRestaurantRecyclerViewAdapter extends RecyclerView.Adapter<FavouriteRestaurantRecyclerViewAdapter.FavouriteViewHolder> {
    public List<Restaurant> restaurantList;
    private Context context;

    public FavouriteRestaurantRecyclerViewAdapter(Context context, List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavouriteRestaurantRecyclerViewAdapter.FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View restaurant_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_favourite_card, parent, false);
        FavouriteViewHolder restaurantVH = new FavouriteViewHolder(restaurant_row);

        return restaurantVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteRestaurantRecyclerViewAdapter.FavouriteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public FavouriteViewHolder(View itemView) {
            super(itemView);


        }

        @Override
        public void onClick(View v) {

        }
    }
}
