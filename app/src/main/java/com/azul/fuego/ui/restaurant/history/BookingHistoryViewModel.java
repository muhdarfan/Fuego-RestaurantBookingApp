package com.azul.fuego.ui.restaurant.history;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azul.fuego.core.Booking;
import com.azul.fuego.core.Fuego;
import com.azul.fuego.core.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class BookingHistoryViewModel extends ViewModel {
    private MutableLiveData<ArrayList<History>> historyLiveData;

    public BookingHistoryViewModel() {
        historyLiveData = new MutableLiveData<>();
        ArrayList<History> bookingDetails = new ArrayList<>();

        Fuego.mStore.collection("restaurants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);

                        if (restaurant.getBookingList() != null) {
                            for (Map<String, Booking> dateFolder : restaurant.getBookingList().values()) {
                                for (Booking booking : dateFolder.values()) {
                                    if (booking.getUserID().equals(Fuego.User.getUid())) {
                                        bookingDetails.add(new History(restaurant.getName(), booking));
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(bookingDetails);
                    historyLiveData.setValue(bookingDetails);
                }
            }
        });
    }

    public MutableLiveData<ArrayList<History>> getBookingLiveData() {
        return historyLiveData;
    }

    public class History implements Comparable<History> {
        private String RestaurantName;
        private Booking BookingDetail;

        public History(String restaurantName, Booking bookingDetail) {
            RestaurantName = restaurantName;
            BookingDetail = bookingDetail;
        }

        public String getRestaurantName() {
            return RestaurantName;
        }

        public void setRestaurantName(String restaurantName) {
            RestaurantName = restaurantName;
        }

        public Booking getBookingDetail() {
            return BookingDetail;
        }

        public void setBookingDetail(Booking bookingDetail) {
            BookingDetail = bookingDetail;
        }

        @Override
        public int compareTo(History o) {
            if (BookingDetail.getBookDate() == null || o.getBookingDetail() == null)
                return 0;

            return o.BookingDetail.getBookDate().compareTo(BookingDetail.getBookDate());
        }
    }
}