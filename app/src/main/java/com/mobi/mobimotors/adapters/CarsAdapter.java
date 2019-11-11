package com.mobi.mobimotors.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobi.mobimotors.R;
import com.mobi.mobimotors.models.Car;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.MyViewHolder>{
Context context;
List<Car> items;
    Picasso picasso = Picasso.get();

    public CarsAdapter(Context context, List<Car>items){
        this.context = context;
        this.items = items;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Car obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i){
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_car,parent,false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(MyViewHolder viewHolder, final int position){
        Car car = items.get(position);
        viewHolder.price.setText(car.getPriceAsString());
        viewHolder.name.setText(car.getName());
        viewHolder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, items.get(position), position);
                }
            }
        });
        //TODO load image using glide
//        Glide.with(context).load("https://images.unsplash.com/photo-1528402671825-9a525ab8b5b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80")
//                .error(R.drawable.ic_whatsapp)
//                .override(175,182)
//                .into(viewHolder.image);
        //load images into image views using picassa
    }
    public int getItemCount(){
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView price,name;
        ConstraintLayout lyt_parent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.car_image);
            price = itemView.findViewById(R.id.car_price);
            name = itemView.findViewById(R.id.car_name);
            lyt_parent = itemView.findViewById(R.id.lyt_parent);

        }
    }
}
