package com.mobi.mobimotors;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobi.mobimotors.adapters.CarsAdapter;
import com.mobi.mobimotors.models.Car;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinalStepFragment extends Fragment {

    List<Car> possibleCarLikesList;
    List<Car>liked;
    private CarsAdapter carsAdapter;

    ImageView carImage;
    TextView carTitle,carPrice;
    int firstPos,lastPos;
    Button likeButton,skipButton,nextButton;
    public FinalStepFragment() {
        // Required empty public constructor

        possibleCarLikesList = new ArrayList<>();
        liked = new ArrayList<>();
        populateAdapter();

        firstPos = 0;
        lastPos = possibleCarLikesList.size();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_final_step, container, false);

        carImage = root.findViewById(R.id.car);
        carTitle = root.findViewById(R.id.car_namee);
        carPrice = root.findViewById(R.id.price);

        //setup the first car
        Car car = possibleCarLikesList.get(0);
        Picasso.get().load(R.drawable.image_car5).into(carImage);
        //set title
        carTitle.setText(car.getName());
        //set the price
        carPrice.setText("UGX "+car.getPrice());

        nextButton =  (Button)root.findViewById(R.id.last);
       nextButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(liked.size()<2){
                   Toast.makeText(getActivity(), "Please like atleast two cars", Toast.LENGTH_SHORT).show();
               }else{
                   Intent i = new Intent(getActivity(),CarListActivity.class);
                   startActivity(i);
               }

           }
       });
       //skip button click
        skipButton =  (Button)root.findViewById(R.id.skip_button);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "skip clic", Toast.LENGTH_SHORT).show();
                firstPos = firstPos+1;
                setupNextCar(firstPos);
            }
        });
        //like button click
        likeButton =  (Button)root.findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "lik clic", Toast.LENGTH_SHORT).show();

                firstPos = firstPos+1;
                setupNextCar(firstPos);
                liked.add(possibleCarLikesList.get(firstPos));

            }
        });
        return root;
    }

    private void setupNextCar(int pos) {
        if(liked.size()==2){
            nextButton.setText("CONTINUE");
        }
        if(firstPos==(possibleCarLikesList.size()-1)){
//            Intent i = new Intent(getActivity(),CarListActivity.class);
//            startActivity(i);

            skipButton.setEnabled(false);
            likeButton.setEnabled(false);
            nextButton.setText("CLICK TO FINISH");
            nextButton.setBackgroundColor(getResources().getColor(R.color.green_500));


            return;

        }
        //setup the first car
        Car car = possibleCarLikesList.get(pos);
        Picasso.get().load(R.drawable.image_car5).into(carImage);
        //set title
        carTitle.setText(car.getName());
        //set the price
        carPrice.setText("UGX "+car.getPrice());

    }

    private void populateAdapter() {
        possibleCarLikesList.add(new Car("Toyota Prado","4000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Wish","2000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Laugmbaug","6000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Noah","7000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Noah","7000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Noah","7000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Noah","7000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Noah","7000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Noah","7000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota Noah3","8000,000",null,null));
        possibleCarLikesList.add(new Car("Toyota veron","6900,000",null,null));

        //fetch cars
//        fetchCarsPossibleCarLikes(); TODO get possible cars from selected question answers
    }

}
