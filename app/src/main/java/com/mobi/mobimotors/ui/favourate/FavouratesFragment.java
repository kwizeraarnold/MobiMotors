package com.mobi.mobimotors.ui.favourate;

import android.os.Bundle;
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

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.mobi.mobimotors.MainActivity;
import com.mobi.mobimotors.R;

import java.util.ArrayList;
import java.util.List;

public class FavouratesFragment extends Fragment {

    private FavouratesViewModel notificationsViewModel;
    private SmartMaterialSpinner spProvince;
    private List<String> provinceList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FavouratesViewModel.class);
        View root = inflater.inflate(R.layout.activity_search, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        initSpinner(root);
        return root;
    }

    private  void initSpinner(View root){
        spProvince = root.findViewById(R.id.spinner1);
        provinceList = new ArrayList<>();
        provinceList.add("Kwizera");
        provinceList.add("Nicholas");
        provinceList.add("Arnold");
        spProvince.setItem(provinceList);
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),provinceList.get(position),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
