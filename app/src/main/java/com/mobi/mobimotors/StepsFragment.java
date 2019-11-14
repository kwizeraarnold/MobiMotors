package com.mobi.mobimotors;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobi.mobimotors.models.Category;
import com.mobi.mobimotors.models.Question;

import java.util.ArrayList;


public class StepsFragment extends Fragment {
    ArrayList<Question> questions;
    String category ="";
    private OnFragmentInteractionListener mListener;
    RadioGroup radioGroup;

    public StepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         = "Small Family";


    }

    private void makeQuestions(String category) {
        questions = new ArrayList<>();
        switch (category){
            case "Small Family":
                questions.add(new Question("Best for Infant Car Seat","Tested by our certified technicians."));
                questions.add(new Question("Best-Rated for Safety","Highest IIHS crash and rollover scores"));
                questions.add(new Question("Most fuel-efficient SUVs","Combined mpg of 30 or greater"));
                questions.add(new Question("Just show me what's popular","I have no preference"));
                break;
            case "Big Family":
                questions.add(new Question("Best for booster seats","Tested by our certified technicians."));
                questions.add(new Question("2nd-Row Captain's Chairs","For easy access to the third row"));
                questions.add(new Question("Most Fuel-Efficient","Combined mpg of 30 or greater"));
                questions.add(new Question("Just show me what's popular","I have no preference"));
                break;
            case "Luxury":
                questions.add(new Question("Classy Coupes","When the backseat takes a backseat"));
                questions.add(new Question("Fabulous Four-Doors","Prestigious style in a refined ride"));
                questions.add(new Question("Sitting Tall","Lavish SUVs and crossover."));
                questions.add(new Question("Just show me what's popular","I have no preference"));
                break;
            case "Style and Comfort":
                questions.add(new Question("Luxury","Driving refinement,upload stying and prestige"));
                questions.add(new Question("Best-Rated for Safety","Highest IIHS crash and rollover scores"));
                questions.add(new Question("Smartphone Connectivity","Has Apple Carplay or Android Auto or both"));
                questions.add(new Question("Just show me what's popular","I have no preference"));
                break;
            case "Eco-Friendly":
                questions.add(new Question("All-Electric Power","Zippy,silent driving with no emissions"));
                questions.add(new Question("Plug-in Hybrids","Gas-engine backup for the open road."));
                questions.add(new Question("Most Fuel Efficient","Combined mpg of 40 or greater,no EVs"));
                questions.add(new Question("Just show me what's popular","I have no preference"));
                break;
            case "Sun Lover":
                questions.add(new Question("Beachgoers","Soft-top style for everyone."));
                questions.add(new Question("Retractable hardtops","Confortable, quite and secure driving."));
                questions.add(new Question("All-Wheel Drive","A convertible for all seasons."));
                questions.add(new Question("Just show me what's popular","I have no preference"));
                break;
            case "Trucks":
                questions.add(new Question("Max Towing","Pickups that tow 100000+ pounds"));
                questions.add(new Question("Off-Road Warrior","Designed to go off the beaten path."));
                questions.add(new Question("Diesel Muscle","When mpg, torque and towing matter most."));
                questions.add(new Question("Just show me what's popular","I have no preference"));
                break;
            default:
                questions.add(new Question("Just show me whats popular","I have no preferene"));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get the chosen category from the data passed to this fragment by the activity
       final Bundle bundle = getArguments();
        category = bundle.getString("category");
        //generate questions based on chosen category
        makeQuestions(category);

        //inflate the question layout
//        View row = getLayoutInflater().inflate(R.layout.row_question,null);

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_steps, container, false);
        /*      get a reference to the new or old or both Radio Group*/
         radioGroup = root.findViewById(R.id.RadioGroup_newOrOldOrBoth);


        //populate the radio group with questions
        final LinearLayout rootLinearLayout = root.findViewById(R.id.linearLayout_fragmentSteps);
        //loop through the questions and add them to the group
        for(Question q: questions){
            View row = getLayoutInflater().inflate(R.layout.row_question,null);
            TextView title =row.findViewById(R.id.TextView_question_title);
            title.setText(q.getName());

            TextView subtitle =row.findViewById(R.id.TextView_question_subtile);
            subtitle.setText(q.getDescription());
            rootLinearLayout.addView(row);
        }
        for(final View v:getViewsByTag(rootLinearLayout,"Radio")){
            ((RadioButton)v).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(getActivity(), view.get, Toast.LENGTH_SHORT).show();

                    for(View v1:getViewsByTag(rootLinearLayout,"Radio")){
                        if(!view.equals(v1)){
                            ((RadioButton)v1).setChecked(false);

                        }
//
                    }
                }
            });
        }

        root.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q1 ="",q2 ="";
                //get the selected question
                for(View v1:getViewsByTag(rootLinearLayout,"Radio")){
                    RadioButton button = (RadioButton)v1;
                    if(button.isChecked()){
                        //if this button is checked get the text of the linear layout in the parent
                        View view = ((LinearLayout) button.getParent()).findViewById(R.id.TextView_question_title);
                        q1 = ((TextView)view).getText().toString();
                        break;
                    }
                }
                if(q1==""){
                    Toast.makeText(getActivity(),"Please select one feature", Toast.LENGTH_SHORT).show();
                    return;
                }

                //get the selected new or old or both

                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if(checkedRadioButtonId!=-1){//if value == -1, no radio button has been selected
//                    Log.d("ID",String.valueOf(checkedRadioButtonId));
                    String t = ((RadioButton)(radioGroup.findViewById(checkedRadioButtonId))).getText().toString();
                    ((ActivityHelper)getActivity()).gotoNextFragment(ActivityHelper.priceFragment);
                }else{
                    Toast.makeText(getActivity(),"Please select on New or Used cars or Both", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    //copied from statck overflow https://stackoverflow.com/questions/8817377/android-how-to-find-multiple-views-with-common-attribute
    private static ArrayList<View> getViewsByTag(ViewGroup root, String tag){
        ArrayList<View> views = new ArrayList<View>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }

        }
        return views;
    }
}
