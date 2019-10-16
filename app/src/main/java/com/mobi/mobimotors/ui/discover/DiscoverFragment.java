package com.mobi.mobimotors.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobi.mobimotors.R;
import com.mobi.mobimotors.adapters.CategoriesAdapter;
import com.mobi.mobimotors.models.Car;
import com.mobi.mobimotors.models.Category;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment {

    private DiscoverViewModel discoverViewModel;
    private CategoriesAdapter categoriesAdapter;
    List<Category> categories;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        discoverViewModel =
            ViewModelProviders.of(this).get(DiscoverViewModel.class);

        View root = inflater.inflate(R.layout.fragment_discover, container, false);

        final TextView textView = root.findViewById(R.id.text_dashboard);
        discoverViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        categories = new ArrayList<Category>();
        RecyclerView recyclerView = root.findViewById(R.id.categories_recyclerView);
        categoriesAdapter = new CategoriesAdapter(getActivity(), categories);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setBackgroundResource(R.color.amber_50);
        recyclerView.setAdapter(categoriesAdapter);
        populateAdapter();

        return root;
    }


    private void populateAdapter() {
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categories.add(new Category("style and luxury","6 seats"));
        categoriesAdapter.notifyDataSetChanged();
    }
}
