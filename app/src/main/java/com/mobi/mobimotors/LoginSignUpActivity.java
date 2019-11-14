package com.mobi.mobimotors;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LoginSignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ConstraintLayout signupLayout = findViewById(R.id.sign_up_activity);
        final ConstraintLayout loginLayout = findViewById(R.id.login_activity);
        signupLayout.setVisibility(View.GONE);
        //sigup button
        TextView sigupTextView =  findViewById(R.id.sign_up);
        signupLayout.setClickable(true);
        sigupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLayout.setVisibility(View.GONE);
                signupLayout.setVisibility(View.VISIBLE);
            }
        });
        //sigup button
        TextView loginOnSigup =  findViewById(R.id.login_textview);
        loginOnSigup.setClickable(true);
        loginOnSigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLayout.setVisibility(View.VISIBLE);
                signupLayout.setVisibility(View.GONE);
            }
        });
    }
}
