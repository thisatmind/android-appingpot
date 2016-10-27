package com.thisatmind.appingpot.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thisatmind.appingpot.R;
import com.thisatmind.appingpot.adapter.HomeAdapter;
import com.thisatmind.appingpot.fragment.pojo.RecoCard;
import com.thisatmind.appingpot.fragment.pojo.TodayCard;

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
        list.add(new RecoCard("appName1", "appImg1"));
        list.add(new TodayCard("todayAppName", "100"));
        return list;
    }

    public static class RecoCardViewHolder extends RecyclerView.ViewHolder {

        private TextView appName;
        private ImageView appImg;
        private CardView card;

        public RecoCardViewHolder(View v) {
            super(v);
            appName = (TextView) v.findViewById(R.id.appName);
            appImg = (ImageView) v.findViewById(R.id.appImg);
            card = (CardView) v.findViewById(R.id.recoCard);
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

        public CardView getCard() { return this.card; }

        public void setCard(CardView card) { this.card = card; }
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