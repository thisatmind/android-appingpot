package com.thisatmind.appingpot.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thisatmind.appingpot.R;
import com.thisatmind.appingpot.activity.InterestActivity;
import com.thisatmind.appingpot.activity.MainActivity;


public class TutorialFragment extends Fragment {

    private static int MAX_COUNT = 4;
    private int count;

    public TutorialFragment() {
        // Required empty public constructor
    }

    public static TutorialFragment newInstance(int count) {
        TutorialFragment fragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putInt("count", count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt("count");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        TypedArray images = getResources().obtainTypedArray(R.array.tutorial_images);
        ImageView image = (ImageView) view.findViewById(R.id.tutorial_image);
        image.setImageResource(images.getResourceId(count, -1));
        images.recycle();

        Resources res = getResources();
        String[] narrationList = res.getStringArray(R.array.tutorial_narrations);
        TextView narration = (TextView) view.findViewById(R.id.tutorial_narration);
        narration.setText(narrationList[count]);


        Button endTutorialBtn = (Button) view.findViewById(R.id.tutorial_end_btn);
        endTutorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreference
                        = getActivity().getSharedPreferences("tutorial", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putBoolean("haveToTutorial", false);
                editor.apply();

                startActivity(new Intent( getContext(), InterestActivity.class));
                getActivity().finish();
            }
        });

        if( count != MAX_COUNT ){
            endTutorialBtn.setEnabled(false);
            endTutorialBtn.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
