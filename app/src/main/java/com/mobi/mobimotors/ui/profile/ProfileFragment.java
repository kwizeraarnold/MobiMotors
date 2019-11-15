package com.mobi.mobimotors.ui.profile;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mobi.mobimotors.LoginSignUpActivity;
import com.mobi.mobimotors.R;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.profile_fragment, container, false);

        //add click functionality to login button
        Button loginButton = root.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginSignUpActivity.class));
            }
        });
        ConstraintLayout loginCard =   root.findViewById(R.id.login_constraintLyt_card);
        SharedPreferences preferences = getActivity().getSharedPreferences("prefs",MODE_PRIVATE);
        String email = preferences.getString("Email","jjh");
//        Boolean loggedIn = preferences.getBoolean("loggedIn",false);
//        if(loggedIn==true){
//            loginButton.setVisibility(View.GONE);
//        }
        return  root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}
