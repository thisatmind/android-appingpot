package com.thisatmind.appingpot.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thisatmind.appingpot.R;
import com.thisatmind.appingpot.models.Usage;

/**
 * Created by sv on 2016-10-17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private static final String TAG = "HomeAdapter";

    private String[] mDataSet;
    private int[] mDataSetTypes;

    public static final int USAGE = 0;
    public static final int RECO = 1;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v){
            super(v);
        }
    }

    public class UsageViewHolder extends ViewHolder {

        public UsageViewHolder(View v){
            super(v);
        }
    }
    public class RecoViewHolder extends ViewHolder {

        public RecoViewHolder(View v){
            super(v);
        }
    }

    public HomeAdapter(String[] dataset, int[] dataSetTypes){
        mDataSet = dataset;
        mDataSetTypes = dataSetTypes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch(viewType){
            case USAGE:
//                v = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.home_usage_card, parent, false);
//                return new UsageViewHolder(v);
            case RECO:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_reco_card, parent, false);
                return new RecoViewHolder(v);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // init data binding
        switch(holder.getItemViewType()){
            case USAGE:
                break;
            case RECO:
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount(){
        return mDataSet.length;
    }

    @Override
    public int getItemViewType(int position){
        return mDataSetTypes[position];
    }
}
