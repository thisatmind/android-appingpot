package com.thisatmind.appingpot.rest.model;

/**
 * Created by mac on 26/10/2016.
 */

public class UserInfo {

    String userType;
    String firebaseId;
    String facebookToken;
    String deviceToken;

    public UserInfo(){}

    public UserInfo(String userType, String firebaseId, String facebookToken, String deviceToken) {
        this.userType = userType;
        this.firebaseId = firebaseId;
        this.facebookToken = facebookToken;
        this.deviceToken = deviceToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
