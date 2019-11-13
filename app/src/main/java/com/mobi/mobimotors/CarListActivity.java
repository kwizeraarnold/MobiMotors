package com.mobi.mobimotors;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.mobi.mobimotors.adapters.CarsAdapter;
import com.mobi.mobimotors.models.Car;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CarListActivity extends AppCompatActivity {
    private ArrayList<Car> cars;
    private CarsAdapter carsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        cars = new ArrayList<Car>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        carsAdapter = new CarsAdapter(this,cars);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setBackgroundResource(R.color.amber_50);
        recyclerView.setAdapter(carsAdapter);

        // on item list clicked
        carsAdapter.setOnItemClickListener(new CarsAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, Car obj, int position) {
                Intent i = new Intent(getApplicationContext(),CarDetailsActivity.class);
                i.putExtra("carName",obj.getName());

                startActivity(i);
            }
        });
        populateAdapter();

    }


    private void populateAdapter() {
        cars.add(new Car("toyota wish","2000000"));
        cars.add(new Car("toyota wish","2000000"));
        cars.add(new Car("toyota wish","2000000"));

        carsAdapter.notifyDataSetChanged();
    }
    }

