package com.thisatmind.appingpot.fragment.pojo;

/**
 * Created by patrick on 2016-10-27.
 */

public class TodayCard {

    private String todayAppName;
    private String todayAppCount;

    public TodayCard() { }

    public TodayCard(String todayAppName, String todayAppCount) {
        this.todayAppName = todayAppName;
        this.todayAppCount = todayAppCount;
    }

    public String getTodayAppName() {
        return todayAppName;
    }

    public void setTodayAppName(String todayAppName) {
        this.todayAppName = todayAppName;
    }

    public String getTodayAppCount() {
        return todayAppCount;
    }

    public void setTodayAppCount(String todayAppCount) {
        this.todayAppCount = todayAppCount;
    }
}
