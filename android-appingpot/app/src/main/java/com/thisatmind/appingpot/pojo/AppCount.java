package com.thisatmind.appingpot.pojo;

/**
 * Created by patrick on 2016-10-08.
 */

public class AppCount {
    private final String packageName;
    private final int count;

    public AppCount(String packageName, int count) {
        this.packageName = packageName;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getPackageName() {
        return packageName;
    }
}
