package com.thisatmind.appingpot.adapter;

import android.app.usage.UsageStats;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thisatmind.appingpot.R;

import java.util.List;

/**
 * Created by patrick on 2016-10-09.
 */

public class RemoveAdapter extends RecyclerView.Adapter<RemoveAdapter.ViewHolder> {
    private Context context;
    private List<UsageStats> usageList;
    private PackageManager pm;

    public RemoveAdapter(List<UsageStats> usageList, PackageManager pm, Context context) {
        this.context = context;
        this.usageList = usageList;
        this.pm = pm;
    }

    public void removeUsageStats(int i){
        this.usageList.remove(i);
    }

    @Override
    public RemoveAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.uninstall_row, viewGroup, false);
        return new RemoveAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RemoveAdapter.ViewHolder viewHolder, int i) {

        viewHolder.uninstallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HERE", "GOGO");
            }
        });
        try{
            viewHolder.appImg.setImageDrawable(pm.getApplicationIcon(usageList.get(i).getPackageName()));
            viewHolder.appName.setText(pm.getApplicationLabel(pm.getApplicationInfo(usageList.get(i).getPackageName(),
                    PackageManager.GET_META_DATA)).toString());
        }catch(Exception e){
            viewHolder.appImg.setImageResource(R.drawable.ic_profile);
            viewHolder.appName.setText(usageList.get(i).getPackageName());
        }
    }

    @Override
    public int getItemCount() {
        return usageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView appImg;
        private TextView appName;
        private Button uninstallBtn ;

        public ViewHolder(View view) {
            super(view);
            appImg = (ImageView) view.findViewById(R.id.uninstall_icon);
            appName = (TextView) view.findViewById(R.id.uninstall_package_name);
            uninstallBtn = (Button) view.findViewById(R.id.uninstall_btn);
        }
    }

}
