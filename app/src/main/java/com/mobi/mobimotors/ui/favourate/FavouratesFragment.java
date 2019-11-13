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
import java.util.Arrays;
import java.util.List;

public class FavouratesFragment extends Fragment {

    private FavouratesViewModel notificationsViewModel;
    private SmartMaterialSpinner spProvince;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FavouratesViewModel.class);
        View root = inflater.inflate(R.layout.activity_search, container, false);
        initSpinner(root,R.id.spinner1,Arrays.<String>asList("Toyota,Mitshubis"));
        initSpinner(root,R.id.spinner2,Arrays.<String>asList("Toyota,Mitshubis"));
        initSpinner(root,R.id.spinner3,Arrays.<String>asList("Toyota,Mitshubis"));

        return root;
    }

    private  void initSpinner(View root, int resource, final List<String>optionsList){
      SmartMaterialSpinner  spinner = root.findViewById(resource);
        spinner.setItem(optionsList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),optionsList.get(position),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
