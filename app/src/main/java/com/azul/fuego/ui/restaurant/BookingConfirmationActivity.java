package com.azul.fuego.ui.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.azul.fuego.R;
import com.azul.fuego.core.Booking;
import com.azul.fuego.core.Fuego;
import com.azul.fuego.core.Restaurant;

public class BookingConfirmationActivity extends AppCompatActivity {
    private Restaurant restaurant;
    private TextView tvID, tvRestaurant, tvDate, tvPeople, tvTime, tvName;
    private Button btnAdd, btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            restaurant = (Restaurant) extras.get("restaurant");
        else
            finish();

        tvID = findViewById(R.id.booking_form_tv_booking_ID);
        tvName = findViewById(R.id.booking_form_et_name);
        tvRestaurant = findViewById(R.id.booking_form_tv_restaurant);
        tvDate = findViewById(R.id.booking_form_tv_date);
        tvPeople = findViewById(R.id.booking_form_tv_people);
        tvTime = findViewById(R.id.booking_form_tv_time);

        btnAdd = findViewById(R.id.booking_form_btn_add_fav);
        btnContinue = findViewById(R.id.booking_form_btn_continue);

        tvID.setText("BOOKING ID: #" + extras.getString("bookingID"));
        tvName.setText(Fuego.UserData.getFullname());
        tvRestaurant.setText(restaurant.getName());
        tvDate.setText(extras.getString("date"));
        tvPeople.setText(extras.getInt("seats") + " people(s)");
        tvTime.setText(extras.getString("time"));

        if (Fuego.UserData.getFavourites().contains(restaurant.getRefID()))
            btnAdd.setVisibility(View.GONE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.setEnabled(false);
                btnAdd.setVisibility(View.GONE);
                Fuego.UserData.getFavourites().add(restaurant.getRefID());
                Fuego.UserData.save();
                Toast.makeText(getApplicationContext(), restaurant.getName() + " has been added to your favourites.", Toast.LENGTH_LONG).show();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}