package com.mobi.mobimotors.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobi.mobimotors.R;
import com.mobi.mobimotors.models.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{
Context context;
List<Category> items;
    public CategoriesAdapter(Context context, List<Category>items){
        this.context = context;
        this.items = items;
    }
    private OnItemClicked onClick;
    public interface OnItemClicked{
        void onItemClick(int position);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i){
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_match,parent,false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(MyViewHolder viewHolder, final int position){
        Category car = items.get(position);
        viewHolder.label.setText(car.getLabel());
        viewHolder.name.setText(car.getName());
        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });

        viewHolder.image.setVisibility(View.VISIBLE);
        viewHolder.image.setBackgroundResource(car.getImageId());
    }
    public int getItemCount(){
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView label,name;
        LinearLayout root;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.category_image);
            label = itemView.findViewById(R.id.category_label);
            name = itemView.findViewById(R.id.category_name);
            root = itemView.findViewById(R.id.linearLayout_root_category);

        }
    }
    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}
