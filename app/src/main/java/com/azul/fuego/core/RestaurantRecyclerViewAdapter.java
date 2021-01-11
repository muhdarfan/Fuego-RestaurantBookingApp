package com.azul.fuego.core;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.azul.fuego.R;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder> implements Filterable {
    public List<Restaurant> restaurantList, itemsFiltered;
    private Context context;

    public RestaurantRecyclerViewAdapter(Context context, List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        this.itemsFiltered = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View restaurant_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_card_restaurant, parent, false);
        RestaurantViewHolder restaurantVH = new RestaurantViewHolder(restaurant_row);

        return restaurantVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.tvName.setText(itemsFiltered.get(position).getName());
        Glide.with(holder.imgResturantImage.getContext()).load(itemsFiltered.get(position).getPhoto_url()).into(holder.imgResturantImage);
        // set image
    }

    @Override
    public int getItemCount() {
        return itemsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                List<Restaurant> filtered = new ArrayList<>();

                if (query.isEmpty()) {
                    filtered = restaurantList;
                } else {
                    for (Restaurant restaurant : restaurantList) {
                        if (restaurant.getName().toLowerCase().contains(query.toLowerCase())) {
                            filtered.add(restaurant);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                itemsFiltered = (ArrayList<Restaurant>) results.values;
                notifyDataSetChanged();
            }
        };
    }



    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName;
        public ImageView imgResturantImage;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.card_restaurant_tv_name);
            imgResturantImage = itemView.findViewById(R.id.card_restaurant_img_main);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("restaurant", restaurantList.get(getAdapterPosition()));
            NavHostFragment.findNavController(FragmentManager.findFragment(v)).navigate(R.id.nav_restaurant_details, bundle);
        }
    }
}