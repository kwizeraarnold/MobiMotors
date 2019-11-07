package com.mobi.mobimotors;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobi.mobimotors.models.Question;

import java.util.ArrayList;


public class StepsFragment extends Fragment {
    ArrayList<Question> questions;

    private OnFragmentInteractionListener mListener;

    public StepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        questions = new ArrayList<>();
        questions.add(new Question("Best for booster seats","Tested by certified staticians"));
        questions.add(new Question("Best for booster seats","Tested by certified staticians"));
        questions.add(new Question("Best for booster seats","Tested by certified staticians"));
        questions.add(new Question("Best for booster seats","Tested by certified staticians"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate the question layout
//        View row = getLayoutInflater().inflate(R.layout.row_question,null);

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_steps, container, false);

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
                ((ActivityHelper)getActivity()).gotoNextFragment();

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
