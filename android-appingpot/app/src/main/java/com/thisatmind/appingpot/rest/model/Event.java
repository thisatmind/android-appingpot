package com.thisatmind.appingpot.rest.model;

/**
 * Created by patrick on 2016-10-02.
 */

public class Event {


    private String userId;

    private String packageName;

    private int count;

    private long createdDate;

    public Event(){}

    public Event(String userId, String packageName, int count, long createdDate) {
        this.userId = userId;
        this.packageName = packageName;
        this.count = count;
        this.createdDate = createdDate;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }
}
