package com.thisatmind.appingpot.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Patrick on 2016-09-17.
 */
public class Event extends RealmObject {

    @PrimaryKey
    @Required
    private String key;
    private String packageName;
    private long date;

    private int count;

    public Event(){}

    public Event(String key, String packageName, long date, int count){
        this.key = key;
        this.packageName = packageName;
        this.date = date;
        this.count = count;
    }

    public com.thisatmind.appingpot.rest.model.Event getPojo(){
        return new com.thisatmind.appingpot.rest.model.Event(
                this.key,this.packageName,this.date,this.count
        );
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }

    public String genKey(String packageName, long date){
        return packageName + date;
    }
}
