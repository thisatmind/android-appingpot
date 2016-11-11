package com.thisatmind.appingpot.rest.model;

/**
 * Created by mac on 26/10/2016.
 */

public class UserInfo {

    String userType;
    String firebaseToken;
    String deviceToken;

    public UserInfo(){}

    public UserInfo(String userType, String firebaseToken, String deviceToken) {
        this.userType = userType;
        this.firebaseToken = firebaseToken;
        this.deviceToken = deviceToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
