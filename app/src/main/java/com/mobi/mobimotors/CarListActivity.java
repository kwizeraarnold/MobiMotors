package com.mobi.mobimotors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobi.mobimotors.adapters.CarsAdapter;
import com.mobi.mobimotors.models.Car;
import com.mobi.mobimotors.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CarListActivity extends AppCompatActivity {
    private ArrayList<Car> carArrayList;
    private CarsAdapter carsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        fetchCars();

        carArrayList = new ArrayList<Car>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        carsAdapter = new CarsAdapter(this, carArrayList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setBackgroundResource(R.color.amber_50);
        recyclerView.setAdapter(carsAdapter);

        // on item list clicked
        carsAdapter.setOnItemClickListener(new CarsAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, Car obj, int position) {
                Intent i = new Intent(getApplicationContext(), CarDetailsActivity.class);
                i.putExtra("carObject", obj);
                Toast.makeText(CarListActivity.this, obj.toString(), Toast.LENGTH_SHORT).show();

                startActivity(i);
            }
        });
        populateAdapter();

    }


    private void populateAdapter() {
        carArrayList.add(new Car("toyota wish", "2000000", null));
        carArrayList.add(new Car("toyota wish", "2000000", null));
        carArrayList.add(new Car("toyota wish", "2000000", null));

        carsAdapter.notifyDataSetChanged();
    }

    private void fetchCars() {
        String url = HomeFragment.API_URL + "/cars";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext(), new HurlStack());
        StringRequest allCarsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray cars = new JSONArray(response);
                    Log.d("tag", cars.toString());
                    carArrayList.clear();
                    int carIdd = -1;
                    for (int i = 0; i < cars.length(); i++) {
                        JSONObject obj = cars.optJSONObject(i);
                        int carId = obj.getInt("car_id");
                        if (carIdd == carId) {
                            continue;
                        }//TODO make the api bring a car with all its images
                        carIdd = carId;
                        String name = obj.getString("make") + " " + obj.getString("model");
                        String price = obj.getString("init_price");
                        String imageUrl = HomeFragment.BASE_URL + obj.getString("path");
                        Log.d("IMAGE", imageUrl);
                        Log.d("ID", String.valueOf(carId));
                        carArrayList.add(new Car(name, price, imageUrl, String.valueOf(carId)));
                    }
                    //make the changes appear on the ui
                    carsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    Toast.makeText(getApplicationContext(), "Ooops Something went wrong when fetching Available carArrayList", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Ooops Something went wrong", Toast.LENGTH_LONG).show();
                Log.d("ERROR", String.valueOf(error));
            }
        });

        requestQueue.add(allCarsRequest);
    }
}
