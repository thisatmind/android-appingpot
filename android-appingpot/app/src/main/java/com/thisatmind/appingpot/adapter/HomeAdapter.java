package com.thisatmind.appingpot.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.thisatmind.appingpot.R;
import com.thisatmind.appingpot.fragment.HomeFragment;
import com.thisatmind.appingpot.fragment.pojo.RecoCard;
import com.thisatmind.appingpot.fragment.pojo.TodayCard;

import java.util.List;

/**
 * Created by sv on 2016-10-17.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;

    private final int RECO = 0, TODAY = 1;

    private Context context;

    public HomeAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof RecoCard) {
            return RECO;
        } else if (items.get(position) instanceof TodayCard) {
            return TODAY;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        this.context = viewGroup.getContext();
        switch (viewType) {
            case RECO:
                View recoCard = inflater.inflate(R.layout.home_reco_card, viewGroup, false);
                viewHolder = new HomeFragment.RecoCardViewHolder(recoCard);
                break;
            case TODAY:
                View todayCard = inflater.inflate(R.layout.home_today_card, viewGroup, false);
                viewHolder = new HomeFragment.TodayCardViewHolder(todayCard);
                break;
            default:
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case RECO:
                HomeFragment.RecoCardViewHolder recoViewHolder = (HomeFragment.RecoCardViewHolder) viewHolder;
                initRecoCardViewHolder(recoViewHolder, position);
                break;
            case TODAY:
                HomeFragment.TodayCardViewHolder todayViewHolder = (HomeFragment.TodayCardViewHolder) viewHolder;
                initTodayCardViewHolder(todayViewHolder, position);
                break;
            default:
                break;
        }
    }

    private void initRecoCardViewHolder(final HomeFragment.RecoCardViewHolder vh, int position) {
        final RecoCard recoCard = (RecoCard) items.get(position);
        if(recoCard != null){
            Glide.with(context)
                .load(recoCard.getAppImg())
                .asBitmap()
                .into(new BitmapImageViewTarget(vh.getAppImg()){
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        super.onResourceReady(bitmap, anim);
                        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {

                                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                                if(vibrantSwatch != null){
                                    Log.d("this", "this is here");
                                    vh.getCardLine().setBackgroundColor(vibrantSwatch.getTitleTextColor());
                                    return;
                                }
                                Log.d("this", "this is not here");
                            }
                        });
                    }
                });
            vh.getAppName().setText(recoCard.getAppName());
            vh.getInstallBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + recoCard.getInstallLink()));
                    context.startActivity(intent);
                }
            });
        }
    }

    private void initTodayCardViewHolder(HomeFragment.TodayCardViewHolder vh1, int position) {
        TodayCard todayCard = (TodayCard) items.get(position);
        if (todayCard != null) {
            vh1.getTodayAppName().setText(todayCard.getTodayAppName());
            vh1.getTodayAppCount().setText(todayCard.getTodayAppCount());
        }
    }
}