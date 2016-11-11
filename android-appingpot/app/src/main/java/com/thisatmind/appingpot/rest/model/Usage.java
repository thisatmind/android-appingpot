package com.thisatmind.appingpot.rest.model;

/**
 * Created by patrick on 2016-11-10.
 */

public class Usage {

    String userId;
    String packageName;
    long date;
    long usage;

    public Usage() {}

    public Usage(String userId, String packageName, long date, long usage) {
        this.userId = userId;
        this.packageName = packageName;
        this.date = date;
        this.usage = usage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getUsage() {
        return usage;
    }

    public void setUsage(long usage) {
        this.usage = usage;
    }
}
