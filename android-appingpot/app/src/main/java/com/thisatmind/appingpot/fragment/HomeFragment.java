package com.thisatmind.appingpot.fragment;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thisatmind.appingpot.R;
import com.thisatmind.appingpot.adapter.HomeAdapter;
import com.thisatmind.appingpot.fragment.pojo.RecoCard;
import com.thisatmind.appingpot.fragment.pojo.TodayCard;
import com.thisatmind.appingpot.models.Event;
import com.thisatmind.appingpot.models.Usage;
import com.thisatmind.appingpot.pojo.AppCount;
import com.thisatmind.appingpot.rest.RestClient;
import com.thisatmind.appingpot.rest.model.EventList;
import com.thisatmind.appingpot.rest.model.ResultInfo;
import com.thisatmind.appingpot.rest.model.UsageList;
import com.thisatmind.appingpot.rest.service.EventService;
import com.thisatmind.appingpot.rest.service.UsageService;
import com.thisatmind.appingpot.tracker.Tracker;
import com.thisatmind.appingpot.tracker.TrackerDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Patrick on 2016-08-04.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Object> items;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.recyclerView = (RecyclerView)rootView.findViewById(R.id.home_recycler_view);
        recyclerView.setAdapter(new HomeAdapter(getItems()));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        try{
            saveUsageData();
            uploadUsageList(TrackerDAO.modelUsageListToRestUsageList(TrackerDAO.getUsageList(
                    TrackerDAO.getUsageStartPoint(getContext())
            )));
            TrackerDAO.setUsageStartPoint(getContext(), TrackerDAO.getHourBefore(Tracker.getUsageStartPoint(getContext())));
            saveEventData();
            uploadEventList(TrackerDAO.modelEventToRestEventList(TrackerDAO.getEventList(
                    TrackerDAO.getEventStartPoint(getContext())
            )));
            TrackerDAO.setEventStartPoint(getContext(), TrackerDAO.getHourBefore(Tracker.getEventStartPoint(getContext())));
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    private List<RecoCard> getRecoCards() {
        final String TAG = "getRecoCards ";
        Log.d(TAG, "start");
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<com.thisatmind.appingpot.models.RecoCard> query =
                realm.where(com.thisatmind.appingpot.models.RecoCard.class);
        RealmResults<com.thisatmind.appingpot.models.RecoCard> result = query.findAll();

        if(result.isEmpty()) return null;

        List<RecoCard> list = new ArrayList<>();
        for(int i = 0; i < result.size(); i++) {
            com.thisatmind.appingpot.models.RecoCard data = result.get(i);
            list.add(new RecoCard(data.getUserId(), data.getKey(), data.getTitle(), data.getIcon(), data.getMarketUrl()));
        }
        return list;
    }

    private List<Object> getItems(){

        ArrayList<Object> list = new ArrayList<>();

        list.addAll(getRecoCards());
        list.add(new TodayCard("카카오톡", "100"));
        return list;
    }

    public void saveEventData(){
        UsageEvents uEvents = Tracker.getUsageEvents(getContext());
        UsageEvents uEventsTimer = Tracker.getUsageEvents(getContext());

        TrackerDAO.saveEventPerHour(Tracker.calcEventPerHour(uEvents,
                Tracker.getEventStartPoint(getContext())));
        long timer = Tracker.calcEventStartPoint(uEventsTimer);

        Tracker.setEventStartPoint(getContext(), timer);
        Log.d("saveEventData", "saved event data");
    }

    public void saveUsageData(){
        UsageEvents uEvents = Tracker.getUsageEvents(getContext());
        UsageEvents uEventsTimer = Tracker.getUsageEvents(getContext());

        TrackerDAO.saveUsagePerHour(Tracker.calcUsagePerHour(uEvents,
                Tracker.getUsageStartPoint(getContext())
        ));
        long timer = Tracker.calcUsageStartPoint(uEventsTimer);

        Tracker.setUsageStartPoint(getContext(), timer);
        Log.d("saveUsageData", "saved usage data");
    }

    public AppCount getAppCount(){
        Event maxEvent = TrackerDAO.getMaxEvent();
        if(maxEvent != null) return new AppCount(maxEvent.getPackageName(), maxEvent.getCount());
        Log.d("MaxEvent", "It is null");
        return null;
    }

    public void uploadEventList(EventList list) {
        EventService service = RestClient.createService(EventService.class);
        Call<ResultInfo> call = service.addEventList(list);
        try{
            call.enqueue(new Callback<ResultInfo>() {
                @Override
                public void onResponse(Call<ResultInfo> call, Response<ResultInfo> response) {
                    Log.d("uploadEventList", response.raw().toString());
                }
                @Override
                public void onFailure(Call<ResultInfo> call, Throwable t) {

                }
            });
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void uploadUsageList(UsageList list){
        UsageService service = RestClient.createService(UsageService.class);
        Call<ResultInfo> call = service.addUsageList(list);
        try{
            call.enqueue(new Callback<ResultInfo>() {
                @Override
                public void onResponse(Call<ResultInfo> call, Response<ResultInfo> response) {
                    Log.d("uploadUsageList", response.raw().toString());
                }
                @Override
                public void onFailure(Call<ResultInfo> call, Throwable t) {

                }
            });
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static class RecoCardViewHolder extends RecyclerView.ViewHolder {

        private TextView appName;
        private ImageView appImg;
        private LinearLayout recoCardLine;
        private Button installBtn;

        public RecoCardViewHolder(View v) {
            super(v);
            appName = (TextView) v.findViewById(R.id.appName);
            appImg = (ImageView) v.findViewById(R.id.appImg);
            recoCardLine = (LinearLayout) v.findViewById(R.id.recoCardLine);
            installBtn = (Button) v.findViewById(R.id.recoAppInstallBtn);
        }

        public TextView getAppName() {
            return this.appName;
        }

        public void setAppName(TextView appName) {
            this.appName = appName;
        }

        public ImageView getAppImg() {
            return this.appImg;
        }

        public void setAppImg(ImageView appImg) {
            this.appImg = appImg;
        }

        public LinearLayout getCardLine() { return this.recoCardLine; }

        public void setCardLine(LinearLayout recoCardLine) { this.recoCardLine = recoCardLine; }

        public Button getInstallBtn() { return this.installBtn;}

        public void setInstallBtn(Button installBtn) { this.installBtn = installBtn;}

    }

    public static class RemoveCardViewHolder extends RecyclerView.ViewHolder {

        private TextView appName;
        private ImageView appImg;
        private TextView lastUsageTime;
        private Button unInsallBtn;

        public RemoveCardViewHolder(View v) {
            super(v);
            appName = (TextView) v.findViewById(R.id.appName);
            appImg = (ImageView) v.findViewById(R.id.appImg);
            lastUsageTime = (TextView) v.findViewById(R.id.lastUsedTime);
            unInsallBtn = (Button) v.findViewById(R.id.uninstall_btn);
        }

        public TextView getAppName() {
            return appName;
        }

        public void setAppName(TextView appName) {
            this.appName = appName;
        }

        public ImageView getAppImg() {
            return appImg;
        }

        public void setAppImg(ImageView appImg) {
            this.appImg = appImg;
        }

        public TextView getLastUsageTime() {
            return lastUsageTime;
        }

        public void setLastUsageTime(TextView lastUsageTime) {
            this.lastUsageTime = lastUsageTime;
        }

        public Button getUnInsallBtn() {
            return unInsallBtn;
        }

        public void setUnInsallBtn(Button unInsallBtn) {
            this.unInsallBtn = unInsallBtn;
        }
    }
    public static class TodayCardViewHolder extends RecyclerView.ViewHolder {

        private TextView todayAppName, todayAppCount;

        public TodayCardViewHolder(View v) {
            super(v);
            todayAppName = (TextView) v.findViewById(R.id.todayAppName);
            todayAppCount = (TextView) v.findViewById(R.id.todayAppCount);
        }

        public TextView getTodayAppName() {
            return this.todayAppName;
        }

        public void setTodayAppName(TextView todayAppName) {
            this.todayAppName = todayAppName;
        }

        public TextView getTodayAppCount() {
            return this.todayAppCount;
        }

        public void setTodayAppCount(TextView todayAppCount) {
            this.todayAppCount = todayAppCount;
        }
    }
}