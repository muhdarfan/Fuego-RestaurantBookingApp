package com.azul.fuego.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azul.fuego.R;

import java.util.List;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Restaurant> restaurantList;
    private Context context;

    public RestaurantRecyclerViewAdapter(Context context, List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View restaurant_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_restaurant, null);
        RestaurantViewHolder restaurantVH = new RestaurantViewHolder(restaurant_row);

        return restaurantVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // TODO
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RestaurantViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
