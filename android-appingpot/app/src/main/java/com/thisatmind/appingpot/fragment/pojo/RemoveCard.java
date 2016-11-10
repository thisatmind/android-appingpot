package com.thisatmind.appingpot.fragment.pojo;

/**
 * Created by patrick on 2016-11-06.
 */

public class RemoveCard {

    private String appName;
    private String appImg;
    private long lastUsedDate;
    private String pacakgeName;

    public RemoveCard(){}

    public RemoveCard(String appName, String appImg, long lastUsedDate, String pacakgeName) {
        this.appName = appName;
        this.appImg = appImg;
        this.lastUsedDate = lastUsedDate;
        this.pacakgeName = pacakgeName;
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

    public long getLastUsedDate() {
        return lastUsedDate;
    }

    public void setLastUsedDate(long lastUsedDate) {
        this.lastUsedDate = lastUsedDate;
    }

    public String getPacakgeName() {
        return pacakgeName;
    }

    public void setPacakgeName(String pacakgeName) {
        this.pacakgeName = pacakgeName;
    }
}
