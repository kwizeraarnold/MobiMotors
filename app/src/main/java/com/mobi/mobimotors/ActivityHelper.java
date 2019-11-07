package com.mobi.mobimotors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityHelper extends AppCompatActivity implements StepsFragment.OnFragmentInteractionListener {
    public static StepsFragment stepsFragment;
    FragmentTransaction fragmentTransaction;
   public  static PriceFragment priceFragment;
   public static FinalStepFragment finalStepFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stepsFragment = new StepsFragment();
        priceFragment = new PriceFragment();
        finalStepFragment = new FinalStepFragment();

        setContentView(R.layout.activity_helper);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = getIntent().getExtras();
        String chosenCategory = bundle.getString("category");
        Toast.makeText(this,chosenCategory,Toast.LENGTH_SHORT).show();
        //send the data in the bundle to the fragment
        stepsFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_root,stepsFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void gotoNextFragment(Fragment fragment){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_root,fragment);
        fragmentTransaction.commit();
    }
}
