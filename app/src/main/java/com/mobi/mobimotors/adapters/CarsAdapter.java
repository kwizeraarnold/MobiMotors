package com.mobi.mobimotors.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobi.mobimotors.R;
import com.mobi.mobimotors.models.Car;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.MyViewHolder>{
Context context;
List<Car> items;
    public CarsAdapter(Context context, List<Car>items){
        this.context = context;
        this.items = items;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i){
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_car,parent,false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(MyViewHolder viewHolder, int position){
        Car car = items.get(position);
        viewHolder.price.setText(car.getPriceAsString());
        viewHolder.name.setText(car.getName());
        //TODO load image using glide
//        Glide.with(context).load("https://images.unsplash.com/photo-1528402671825-9a525ab8b5b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80")
//                .error(R.drawable.ic_whatsapp)
//                .override(175,182)
//                .into(viewHolder.image);
    }
    public int getItemCount(){
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView price,name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.car_image);
            price = itemView.findViewById(R.id.car_price);
            name = itemView.findViewById(R.id.car_name);

        }
    }
}
