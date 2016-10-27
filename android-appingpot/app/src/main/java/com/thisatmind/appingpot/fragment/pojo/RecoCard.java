package com.thisatmind.appingpot.fragment.pojo;

/**
 * Created by patrick on 2016-10-27.
 */

public class RecoCard {

    private String appName;
    private String appImg;

    public RecoCard() {}

    public RecoCard(String appName, String appImg) {
        this.appName = appName;
        this.appImg = appImg;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppImg() {
        return appImg;
    }

    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }
}
