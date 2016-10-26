package com.thisatmind.appingpot.rest.model;

/**
 * Created by mac on 26/10/2016.
 */

public class UserInfo {

    String id;
    String userType;
    String name;

    public UserInfo(){}

    public UserInfo(String id, String userType, String name) {
        this.id = id;
        this.userType = userType;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
