package com.thisatmind.appingpot.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thisatmind.appingpot.R;

/**
 * Created by Patrick on 2016-08-04.
 */
public class HomeTwoFragment extends Fragment {
    public HomeTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("HomeTwoFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_two_home, container, false);
    }
}
