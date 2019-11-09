package com.mobi.mobimotors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CarDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        Bundle carName = getIntent().getExtras();
        String carName1 = carName.getString("carName");
        if(carName1!=null){
            Toast.makeText(this, carName1.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
