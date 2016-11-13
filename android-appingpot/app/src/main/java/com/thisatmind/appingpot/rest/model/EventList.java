package com.thisatmind.appingpot.rest.model;

import java.util.List;

/**
 * Created by patrick on 2016-10-02.
 */

public class EventList {

    List<Event> list;

    public EventList(){}

    public EventList(List<Event> list){
        this.list = list;
    }

    public List<Event> getEventList(){
        return this.list;
    }

    public void setEventList(List<Event> list){
        this.list = list;
    }
}
