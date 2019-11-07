package com.mobi.mobimotors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityHelper extends AppCompatActivity implements StepsFragment.OnFragmentInteractionListener {
    StepsFragment stepsFragment;
    FragmentTransaction fragmentTransaction;
    PriceFragment priceFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stepsFragment = new StepsFragment();
        priceFragment = new PriceFragment();

        setContentView(R.layout.activity_helper);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = getIntent().getExtras();
        String chosenCategory = bundle.getString("category");
        Toast.makeText(this,chosenCategory,Toast.LENGTH_SHORT).show();

        fragmentTransaction.replace(R.id.fragment_root,stepsFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void gotoNextFragment(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_root,priceFragment);
        fragmentTransaction.commit();
    }
}
