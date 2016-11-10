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
import com.thisatmind.appingpot.pojo.AppCount;
import com.thisatmind.appingpot.tracker.Tracker;
import com.thisatmind.appingpot.tracker.TrackerDAO;

import java.util.ArrayList;
import java.util.List;

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
        return rootView;
    }

    private List<Object> getItems(){
        ArrayList<Object> list = new ArrayList<>();
        list.add(new RecoCard("토스", "https://lh3.googleusercontent.com/R2IN1j3Um3mttEwE4wCBgwlntTOoDCevffLdK-IsYA0kn8KA21OoKx4KAGNVB-jw7Mk=w300-rw", "viva.republica.toss"));
        list.add(new RecoCard("자소설닷컴", "https://lh3.googleusercontent.com/6wjXIkv0r7ETwf2ewj3kupCjneQbiuSftzTte5ZXrjErMztjmeH4lrGwJqLa43VUkDU=w300-rw", "com.anchoreer.jasoseol"));
        list.add(new RecoCard("it알려줌", "https://lh3.googleusercontent.com/bQExpSSf4T7_XW23qTOOump2iEQJ_pWqebgUYwWsdRk-gcIM6nVLGLTF6WWk4hwW7KE=w300-rw", "com.allyeozum.android.allyeozumit"));
        list.add(new RecoCard("YBM TOEIC Speaking Test", "https://lh3.googleusercontent.com/jFNT6jHS9yKxKhNOrpWL9FbRuMsDIxcrTaJZ3s6tXnZjK1GYQkaMZmt7SNolMQWboA=w300-rw", "com.ybmsisa.TosEx"));
        list.add(new RecoCard("잡코리아", "https://lh4.ggpht.com/BGoj8lgJy-VLSzkSyidpvpqrtICgQjFuOAe6GSdD_tlnZJobUeTw_-49OihHPzFnrvM=w300-rw", "com.jobkorea.app"));
        list.add(new RecoCard("Airbnb", "https://lh3.googleusercontent.com/BQnvuZR500pg2ulvv3FBmRI93ODz3AjNfbz92hCieuJLvmbGY36AKhETMTTfTDgpPQI=w300-rw", "com.airbnb.android"));
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

    public AppCount getAppCount(){
        Event maxEvent = TrackerDAO.getMaxEvent();
        if(maxEvent != null) return new AppCount(maxEvent.getPackageName(), maxEvent.getCount());
        Log.d("MaxEvent", "It is null");
        return null;
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