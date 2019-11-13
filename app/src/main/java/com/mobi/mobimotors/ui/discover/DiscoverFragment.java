package com.mobi.mobimotors.ui.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobi.mobimotors.ActivityHelper;
import com.mobi.mobimotors.R;
import com.mobi.mobimotors.adapters.CategoriesAdapter;
import com.mobi.mobimotors.models.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscoverFragment extends Fragment {

    private DiscoverViewModel discoverViewModel;
    private CategoriesAdapter categoriesAdapter;

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
//        List<Category> categories = new ArrayList<Category>();
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
        List<Category> category1 = Arrays.asList(new Category("Small Family","5 seats"),new Category("Big Family","6 seats"));
        List<Category> category2 = Arrays.asList(new Category("Good Fuel Comsumption",""),new Category("Car Shape",""));
        List<Category> category3 = Arrays.asList(new Category("Luxury",""),new Category("Confort",""));
        List<Category> category4 = Arrays.asList(new Category("Upcountry Car",""));
//        RecyclerView recyclerView = root.findViewById(R.id.family_recyclerView);
//        categoriesAdapter = new CategoriesAdapter(getActivity(), categories);
//        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
//        recyclerView.setLayoutManager(manager);
////        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setBackgroundResource(R.color.amber_50);
//        recyclerView.setAdapter(categoriesAdapter);
//        populateAdapter();
        makeAdapter(category1,R.id.family_recyclerView,root);
        makeAdapter(category2,R.id.city_worker_recyclerView,root);
        makeAdapter(category3,R.id.luxury_recyclerView,root);
        makeAdapter(category4,R.id.work_horse_recyclerView,root);
        return root;
    }

    /**
     * this methods associates recyclerviews with their adapters and arraylists
     * @param list
     * @param recyclerViewId
     */
    public void makeAdapter(final List list, int recyclerViewId, View root){
        RecyclerView recyclerView = root.findViewById(recyclerViewId);
//        categoriesAdapter = new CategoriesAdapter(getActivity(), list);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setBackgroundResource(R.color.amber_50);
        final CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), list);
        categoriesAdapter.setOnClick(new CategoriesAdapter.OnItemClicked() {
            @Override
            public void onItemClick(int position) {
                Category category = (Category) list.get(position);


//                Toast.makeText(getActivity(),category.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ActivityHelper.class);
                intent.putExtra("category",category.getName());//send the chosen category to the activity helper class
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(categoriesAdapter);


    }


//    private void populateAdapter() {
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categories.add(new Category("style and luxury","6 seats"));
//        categoriesAdapter.notifyDataSetChanged();
//    }
}
