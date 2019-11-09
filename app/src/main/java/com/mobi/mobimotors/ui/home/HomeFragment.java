package com.mobi.mobimotors.ui.home;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobi.mobimotors.CarDetailsActivity;
import com.mobi.mobimotors.R;
import com.mobi.mobimotors.adapters.CarsAdapter;
import com.mobi.mobimotors.models.Car;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<Car> cars;
    private CarsAdapter carsAdapter;

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

        cars = new ArrayList<Car>();
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        carsAdapter = new CarsAdapter(getActivity(),cars);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setBackgroundResource(R.color.amber_50);
        recyclerView.setAdapter(carsAdapter);

        carsAdapter.setOnItemClickListener(new CarsAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, Car obj, int position) {
                Intent i = new Intent(getActivity(), CarDetailsActivity.class);
                startActivity(i);
            }
        });
        populateAdapter();




        return root;
    }


    private void populateAdapter() {
        cars.add(new Car("toyota wish",2000000));
        cars.add(new Car("toyota beast",20000000));
        cars.add(new Car("toyota mastag",5000000));
        cars.add(new Car("toyota mastag",5000000));
        cars.add(new Car("toyota mastag",5000000));
        cars.add(new Car("toyota mastag",5000000));
        carsAdapter.notifyDataSetChanged();
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
