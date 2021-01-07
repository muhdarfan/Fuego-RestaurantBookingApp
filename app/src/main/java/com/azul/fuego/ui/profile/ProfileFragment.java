package com.azul.fuego.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.azul.fuego.R;
import com.azul.fuego.core.Restaurant;
import com.azul.fuego.core.RestaurantRecyclerViewAdapter;
import com.azul.fuego.ui.home.HomeViewModel;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

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

        mViewModel.getUserDataMutableLiveData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                String name = firebaseUser.getDisplayName();
                String phoneNumber = firebaseUser.getPhoneNumber();

                nickname.setText(name.split(" ")[0].trim());
                fullName.setText(name);
                email.setText(firebaseUser.getEmail());
                phone.setText(TextUtils.isEmpty(phoneNumber) ? "No phone number attached." : phoneNumber);
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