package com.thisatmind.appingpot.rest.model;

/**
 * Created by patrick on 2016-11-10.
 */

public class Usage {

    String userId;
    String appId;
    String dailyUsageTime;
    String dailyUsageCount;

    public Usage() {}

    public Usage(String userId, String appId, String dailyUsageTime, String dailyUsageCount) {
        this.userId = userId;
        this.appId = appId;
        this.dailyUsageTime = dailyUsageTime;
        this.dailyUsageCount = dailyUsageCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDailyUsageTime() {
        return dailyUsageTime;
    }

    public void setDailyUsageTime(String dailyUsageTime) {
        this.dailyUsageTime = dailyUsageTime;
    }

    public String getDailyUsageCount() {
        return dailyUsageCount;
    }

    public void setDailyUsageCount(String dailyUsageCount) {
        this.dailyUsageCount = dailyUsageCount;
    }
}
