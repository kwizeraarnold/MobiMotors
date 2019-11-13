package com.mobi.mobimotors.ui.home;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.mobi.mobimotors.CarDetailsActivity;
import com.mobi.mobimotors.R;
import com.mobi.mobimotors.adapters.CarsAdapter;
import com.mobi.mobimotors.models.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String BASE_URL ="http://www.blackbooksuganda.com" ;
    private static final String API_URL ="http://www.blackbooksuganda.com/api" ;
    private HomeViewModel homeViewModel;
    private List<Car> carList;
    private CarsAdapter carsAdapter;
    Toolbar toolbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        carList = new ArrayList<Car>();
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        carsAdapter = new CarsAdapter(getActivity(), carList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(carsAdapter);

        carsAdapter.setOnItemClickListener(new CarsAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, Car obj, int position) {
                Intent i = new Intent(getActivity(), CarDetailsActivity.class);
                i.putExtra("carName",obj.getName());
                startActivity(i);
            }
        });
        populateAdapter();





        return root;
    }


    private void populateAdapter() {
//        carList.add(new Car("Toyota Wishg",200000));
//        carList.add(new Car("Toyota Beast",200000));
//        carList.add(new Car("Toyota Mastag",500000));
//        carList.add(new Car("Toyota Mastag",500000));
//        carList.add(new Car("Toyota Mastag",500000));
//        carList.add(new Car("Toyota Mastag",500000));
//        carsAdapter.notifyDataSetChanged();
        //fetch cars
        fetchCars();
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private void fetchCars() {
        String url =API_URL+"/cars";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity(), new HurlStack());
        StringRequest allCarsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray cars = new JSONArray(response);
                    Log.d("tag",cars.toString());
                    carList.clear();
                    int carIdd = -1;
                    for(int i = 0; i< cars.length(); i++){
                        JSONObject obj = cars.optJSONObject(i);
                        int carId = obj.getInt("car_id");
                        if(carIdd==carId){
                            continue;
                        }
                        carIdd = carId;
                        String name = obj.getString("make")+" "+obj.getString("model");
                        String price = obj.getString("init_price");
                        String imageUrl =BASE_URL+ obj.getString("path");
                        Log.d("IMAGE",imageUrl);
                        carList.add(new Car(name,price,imageUrl));//TODO get car name and price
                    }
                    //make the changes appear on the ui
                    carsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    Toast.makeText(getActivity(), "Ooops Something went wrong when fetching Available cars", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Ooops Something went wrong", Toast.LENGTH_LONG).show();
                Log.d("ERROR", String.valueOf(error));
            }
        });

        requestQueue.add(allCarsRequest);
    }

}
