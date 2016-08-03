package com.thisatmind.appingpot;

/**
 * Created by Patrick on 2016-08-03.
 */

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<ApplicationInfo> apps;
    private PackageManager pm;

    public DataAdapter(ArrayList<ApplicationInfo> apps) {
        this.apps = apps;
    }

    public DataAdapter(List<ApplicationInfo> apps, PackageManager pm) {
        this.apps = apps;
        this.pm = pm;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.appName.setText(pm.getApplicationLabel(apps.get(i)).toString());
        viewHolder.appPackageName.setText(apps.get(i).packageName);
        viewHolder.appImg.setImageDrawable(pm.getApplicationIcon(apps.get(i)));
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView appName;
        private TextView appPackageName;
        private ImageView appImg;

        public ViewHolder(View view) {
            super(view);
            appImg = (ImageView)view.findViewById(R.id.appImg);
            appName = (TextView)view.findViewById(R.id.appName);
            appPackageName = (TextView)view.findViewById(R.id.appPackageName);
        }
    }

}