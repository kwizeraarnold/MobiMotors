package com.mobi.mobimotors.ui.favourate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.mobi.mobimotors.MainActivity;
import com.mobi.mobimotors.R;
import com.mobi.mobimotors.models.Car;
import com.mobi.mobimotors.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FavouratesFragment extends Fragment {

    private FavouratesViewModel notificationsViewModel;
    private SmartMaterialSpinner spProvince;
    private HashMap<String,String>make,model;
    ArrayList<String> list,modelList;
    View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        make = new HashMap<>();
        model = new HashMap<>();
        list = new ArrayList<>();
        modelList = new ArrayList<>();

        fetchMake(make);
//        fetchMakeModels(,model);
        notificationsViewModel =
                ViewModelProviders.of(this).get(FavouratesViewModel.class);
        View root = inflater.inflate(R.layout.activity_search, container, false);

//        initSpinner(root,R.id.spinner1,new ArrayList<String>(make.values()));
//        initSpinner(root,R.id.spinner2,new ArrayList<String>(model.values()));


        rootView = root;
        return root;
    }

    private  void initSpinner(View root, int resource, final List<String>optionsList){
      SmartMaterialSpinner  spinner = root.findViewById(resource);
        spinner.setItem(optionsList);
        spinner.setSearchable(true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),optionsList.get(position)+String.valueOf(position),
                        Toast.LENGTH_SHORT).show();
                fetchMakeModels(String.valueOf(position+1),model);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void fetchMake(final HashMap<String,String>map) {
        String url = HomeFragment.API_URL+"/makes";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity(), new HurlStack());
        StringRequest allCarsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arrayObj = new JSONArray(response);
                    for(int i = 0; i< arrayObj.length(); i++){
                        JSONObject obj = arrayObj.optJSONObject(i);
                        int id = obj.getInt("id");
                        String value = obj.getString("make");
                        map.put(String.valueOf(id),value);
                        list.add(value);
                    }
                    Log.d("MAP",make.toString());
                    Toast.makeText(getActivity(), String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
                    initSpinner(rootView,R.id.spinner3,list );

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
    private void fetchMakeModels(String make_id, final HashMap<String,String>map) {
        String url = HomeFragment.API_URL+"/models/"+make_id;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity(), new HurlStack());
        StringRequest allCarsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arrayObj = new JSONArray(response);
                    for(int i = 0; i< arrayObj.length(); i++){
                        JSONObject obj = arrayObj.optJSONObject(i);
                        int id = obj.getInt("id");
                        String value = obj.getString("model");
                        map.put(String.valueOf(id),value);
                        modelList.add(value);
                    }
                    SmartMaterialSpinner  spinner = rootView.findViewById(R.id.spinner2);
                    spinner.setItem(modelList);

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
