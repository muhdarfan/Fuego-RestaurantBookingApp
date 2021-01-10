package com.azul.fuego.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azul.fuego.R;
import com.azul.fuego.ui.restaurant.history.BookingHistoryViewModel;

import java.util.Date;
import java.util.List;

public class BookingHistoryRecyclerViewAdapter extends RecyclerView.Adapter<BookingHistoryRecyclerViewAdapter.BookingViewHolder> {
    public List<BookingHistoryViewModel.History> bookingList;
    private Context context;

    public BookingHistoryRecyclerViewAdapter(Context context, List<BookingHistoryViewModel.History> bookingList) {
        this.bookingList = bookingList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View booking_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_booking_history, parent, false);
        BookingHistoryRecyclerViewAdapter.BookingViewHolder bookingVH = new BookingHistoryRecyclerViewAdapter.BookingViewHolder(booking_row);

        return bookingVH;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Date date = bookingList.get(position).getBookingDetail().getBookDate().toDate();
        holder.tvName.setText(bookingList.get(position).getRestaurantName());
        holder.tvDate.setText(String.format(date.toString(), "E, MMM dd yyyy"));
        holder.tvTime.setText(bookingList.get(position).getBookingDetail().getBooked_time());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }


    public class BookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName, tvDate, tvTime;
        public BookingViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.rv_booking_tv_name);
            tvDate = itemView.findViewById(R.id.rv_booking_tv_date);
            tvTime = itemView.findViewById(R.id.rv_booking_tv_time);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
