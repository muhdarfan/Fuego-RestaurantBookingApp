package com.azul.fuego.ui.profile;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.azul.fuego.R;
import com.azul.fuego.core.Users;

public class ProfileFragment extends Fragment {
    private TextView nickname, fullName, email, phone;
    private ProfileViewModel mViewModel;
    protected ImageView editBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nickname = getView().findViewById(R.id.profile_tv_short_name);
        fullName = getView().findViewById(R.id.profile_tv_name);
        email = getView().findViewById(R.id.profile_tv_email);
        phone = getView().findViewById(R.id.profile_tv_phone);
        editBtn = getView().findViewById(R.id.profile_iv_edit);

        mViewModel.getUserDataMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Users>() {
            @Override
            public void onChanged(Users users) {
                fullName.setText(users.getFullname());
                nickname.setText(users.getFullname().split(" ")[0]);
                email.setText(users.getEmail());
                phone.setText(TextUtils.isEmpty(users.getPhone()) ? "No phone number attached." : users.getPhone());
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProfileFragment.this).navigate(R.id.nav_profile_edit);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

}