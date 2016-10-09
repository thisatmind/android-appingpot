package com.thisatmind.appingpot.fragment;

import android.app.usage.UsageEvents;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thisatmind.appingpot.R;
import com.thisatmind.appingpot.databinding.FragmentTwoHomeBinding;
import com.thisatmind.appingpot.models.Event;
import com.thisatmind.appingpot.pojo.AppCount;
import com.thisatmind.appingpot.tracker.Tracker;
import com.thisatmind.appingpot.tracker.TrackerDAO;

import io.realm.Realm;

/**
 * Created by Patrick on 2016-08-04.
 */
public class HomeTwoFragment extends Fragment {

    private FragmentTwoHomeBinding dataBinding;
    private Realm realm;
    private AppCount appCount;

    public HomeTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("HomeTwoFragment", "onCreateView");

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_two_home, container, false);
        View view = dataBinding.getRoot();
        this.appCount = getAppCount();

        final PackageManager pm = getActivity().getPackageManager();
        try{
            this.appCount = new AppCount(pm.getApplicationLabel(pm.getApplicationInfo(appCount.getPackageName(),
                    PackageManager.GET_META_DATA)).toString(),
                    this.appCount.getCount());
        }catch(Exception e){
            e.printStackTrace();
        }
        dataBinding.setAppCount(this.appCount);
        dataBinding.setFragment(this);
        return view;
    }

    public void saveEventData(){
        UsageEvents uEvents = Tracker.getUsageEvents(getContext());
        UsageEvents uEventsTimer = Tracker.getUsageEvents(getContext());

        TrackerDAO.saveEventPerHour(Tracker.calcEventPerHour(uEvents,
                Tracker.getEventStartPoint(getContext())));
        long timer = Tracker.calcEventStartPoint(uEventsTimer);
        Tracker.setEventStartPoint(getContext(), timer);
        Log.d("saveEventData", "saved event data");
        this.appCount = getAppCount();
    }

    public AppCount getAppCount(){
        Event maxEvent = TrackerDAO.getMaxEvent();
        if(maxEvent != null) return new AppCount(maxEvent.getPackageName(), maxEvent.getCount());
        Log.d("MaxEvent", "It is null");
        return null;
    }
}
