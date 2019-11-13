package com.mobi.mobimotors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobi.mobimotors.models.Car;
import com.mobi.mobimotors.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CarDetailsActivity extends AppCompatActivity {

    ImageView carImage;
    TextView carTitle,carPrice,carColor,carTransmission,carDriveTerrain,carGasDesc;
    int carId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        carImage = findViewById(R.id.detail_car_image);
        carTitle = findViewById(R.id.car_title);
        carPrice = findViewById(R.id.detail_car_price);
        carColor = findViewById(R.id.exterior_color_desc);
        carTransmission = findViewById(R.id.transmission_desc);
        carDriveTerrain = findViewById(R.id.drivetrain_desc);
        carGasDesc = findViewById(R.id.gas_desc);

        initToolbar();

        Intent i = getIntent();
        Car car = (Car)i.getSerializableExtra("carObject");
        if(car==null){
            Toast.makeText(this,"failed to load car details", Toast.LENGTH_SHORT).show();

        }else{
            Picasso.get().load(car.getImageUrl()).into(carImage);
            carTitle.setText(car.getName());
            carPrice.setText("UGX "+car.getPrice());
            fetchCarDetails(car.getId());

        }
    }

    private void fetchCarDetails(String id) {
        Log.d("IDD",String.valueOf(id));
        String url = HomeFragment.API_URL+"/cars"+"/"+id;
        RequestQueue requestQueue = Volley.newRequestQueue(this, new HurlStack());
        StringRequest allCarsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray car = new JSONArray(response);
                    JSONObject obj = car.optJSONObject(0);
                    carGasDesc.setText(obj.getString("fuel_type"));
                    carColor.setText(obj.getString("car_color"));
                    carTransmission.setText(obj.getString("transition"));
                    carDriveTerrain.setText(obj.getString("wheel_drive"));

                } catch (JSONException e) {

                    Toast.makeText(getApplicationContext(), "Ooops Something went wrong when fetching Car Details", Toast.LENGTH_LONG).show();
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

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void bookcarAction(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_book_summary);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);

        ((Button) dialog.findViewById(R.id.bt_okay)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });

        dialog.show();
    }
}
