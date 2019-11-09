package com.mobi.mobimotors;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import io.apptik.widget.MultiSlider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PriceFragment extends Fragment {
    View root;
    TextView minPrice,maxPrice;
    TextView minAmount,maxAmount;
    int min =1000000;
    int max = 2000000;
    int range = (max-min)/90;


    public PriceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root = inflater.inflate(R.layout.fragment_price, container, false);
        minPrice = root.findViewById(R.id.TextView_minPrice);
        maxPrice = root.findViewById(R.id.TextView_maxPrice);
        minAmount = root.findViewById(R.id.textView_min_amount);
        maxAmount = root.findViewById(R.id.textView_max_amount);

        Toast.makeText(getActivity(),String.valueOf(range),Toast.LENGTH_SHORT).show();
        //set the min and max amount
        minAmount.setText("UGX "+String.valueOf(min));
        minPrice.setText("UGX "+String.valueOf(min));
        maxAmount.setText("UGX "+String.valueOf(max));
        maxPrice.setText("UGX "+String.valueOf(max));
        root.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SeekBar seekBar = root.findViewById(R.id.SeekBar_FragmentPrice_Price);
//                int minimumHeight = seekBar.getMinimumHeight();
//                int maximumHeight = seekBar.getMax();
//                Toast.makeText(getActivity(), "Min"+minimumHeight+"Max"+maximumHeight, Toast.LENGTH_SHORT).show();
                ((ActivityHelper)getActivity()).gotoNextFragment(ActivityHelper.finalStepFragment);

            }
        });
        MultiSlider multiSlider5 = (MultiSlider)root.findViewById(R.id.SeekBar_FragmentPrice_Price);
        multiSlider5.setMin(0);

        multiSlider5.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider,
                                       MultiSlider.Thumb thumb,
                                       int thumbIndex,
                                       int value)
            {

                if (thumbIndex == 0) {
                    minPrice.setText("UGX "+String.valueOf(value*range));
                }

                if(thumbIndex==1){
                    maxPrice.setText("UGX "+String.valueOf(value*range));
                }
            }
        });
        return root;
    }

}
