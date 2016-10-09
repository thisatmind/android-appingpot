package com.thisatmind.appingpot.fragment;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.thisatmind.appingpot.R;
import com.thisatmind.appingpot.adapter.RemoveAdapter;
import com.thisatmind.appingpot.tracker.Tracker;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Patrick on 2016-08-04.
 */
public class HomeThreeFragment extends Fragment {
    public HomeThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("HomeThreeFragment", "onCreateView");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_three_home, container, false);

        // RecyclerView
        final Activity activity = ((AppCompatActivity)getActivity());
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.remove_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Tracker tracker  = new Tracker();
        final List<ApplicationInfo> appList = tracker.getInstalledAppList(activity);
        final List<UsageStats> usageList = Tracker.getUsageStatsList(activity);
        PackageManager pm = activity.getPackageManager();


//        HashMap<String, UsageStats> map = new HashMap<>();

        for(int i = 0 ; i < usageList.size() ; i++){
            Log.d("USAGE", usageList.get(i).getPackageName());
            Log.d("USAGE", Long.toString(usageList.get(i).getLastTimeStamp()));
        }


        Collections.sort(usageList, new Comparator<UsageStats>(){
           public int compare(UsageStats stat1, UsageStats stat2){
               Log.d("COMPARE", stat1.getPackageName() + " : " + Long.toString(stat1.getLastTimeStamp()) +
               " / " + stat2.getPackageName()+ " : " +Long.toString(stat2.getLastTimeStamp()));
               return stat1.getLastTimeStamp() < stat2.getLastTimeStamp() ? -1 : 1;
           }
        });

        RecyclerView.Adapter adapter = new RemoveAdapter(usageList, pm, getContext());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(activity.getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    Intent intent = new Intent(Intent.ACTION_DELETE);
                    intent.setData(Uri.parse("package:"+usageList.get(position).getPackageName()));
                    startActivity(intent);
//                    usageList.remove(getAdapterPosition());
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return rootView;
    }
}
