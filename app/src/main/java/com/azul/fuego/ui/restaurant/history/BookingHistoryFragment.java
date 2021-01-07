package com.azul.fuego.ui.restaurant.history;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azul.fuego.R;

public class BookingHistoryFragment extends Fragment {

    private BookingHistoryViewModel mViewModel;

    public static BookingHistoryFragment newInstance() {
        return new BookingHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.booking_history_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BookingHistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}