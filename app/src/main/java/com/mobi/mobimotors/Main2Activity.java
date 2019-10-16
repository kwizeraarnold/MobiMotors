package com.mobi.mobimotors;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_discover, R.id.navigation_favourate,R.id.navigation_profile)
                .build();
        //getting the navigation controller
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //changes the action bar title when a navigation icon is clicked
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //setup the bottom navigation to be used with the navigation controller
        NavigationUI.setupWithNavController(navView, navController);
    }

}
