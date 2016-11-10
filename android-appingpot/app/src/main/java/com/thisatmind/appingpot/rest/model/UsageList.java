package com.thisatmind.appingpot.rest.model;

import java.util.List;

/**
 * Created by patrick on 2016-09-25.
 */
public class UsageList {

    List<Usage> list;

    public UsageList(){}

    public UsageList(List<Usage> list) {
        this.list = list;
    }

    public List<Usage> getList() {
        return list;
    }

    public void setList(List<Usage> list) {
        this.list = list;
    }
}
