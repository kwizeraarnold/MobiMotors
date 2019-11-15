package com.mobi.mobimotors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LoginSignUpActivity extends AppCompatActivity {
    EditText emailSignup,emailLogin,passwordLogin,passwordSignup,passwordComformSignup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ConstraintLayout signupLayout = findViewById(R.id.sign_up_activity);
        final ConstraintLayout loginLayout = findViewById(R.id.login_activity);
//        signupLayout.setVisibility(View.GONE);
        //get all edit text
        emailSignup = findViewById(R.id.email_signup_editText);
        emailLogin = findViewById(R.id.email_login_editText);
        //get passwords
        passwordLogin = findViewById(R.id.login_password);
        passwordSignup = findViewById(R.id.password_signup);
        passwordComformSignup = findViewById(R.id.confirmpassword_signup);

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

        //sigup button
        Button createAccount =  findViewById(R.id.create_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get email
                String email = emailSignup.getText().toString();

                //get password
                String password = passwordSignup.getText().toString();
                //get conform passworkd
                String conformPassword = passwordComformSignup.getText().toString();

                //compare passwords
                if(password.equals(conformPassword)){
                    //save
                    SharedPreferences.Editor editor = getSharedPreferences("prefs",MODE_PRIVATE).edit();
                    editor.putString("Email",email);
                    editor.putString("Password",password);
                    editor.putBoolean("loggedIn",true);
                    editor.commit();

                    //if match go to activitu
                    startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                }else{
                    Toast.makeText(LoginSignUpActivity.this,"Passwords dont match", Toast.LENGTH_SHORT).show();
                }

                loginLayout.setVisibility(View.VISIBLE);
                signupLayout.setVisibility(View.GONE);
            }
        });
        //sigup button
        Button loginButton =  findViewById(R.id.login_loggin_button);
//        if(loginButton!=)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
                String prefe = preferences.getString("Email","");
                String prefP = preferences.getString("Password","");

                //get email
                String email = emailLogin.getText().toString();

                //get password
                String password = passwordLogin.getText().toString();
                //get conform passworkd

                //compare passwords
                if(prefe.equals(email)){
                        if(prefP.equals(password)){
                            SharedPreferences.Editor editor = getSharedPreferences("prefs",MODE_PRIVATE).edit();
                            editor.putBoolean("loggedIn",true);
                            editor.commit();
                            //if match go to activitu
                            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                        }else{
                            Toast.makeText(LoginSignUpActivity.this, "Incorrent Password", Toast.LENGTH_SHORT).show();
                        }

                }else{
                    Toast.makeText(LoginSignUpActivity.this, "User email not found", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
