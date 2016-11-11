package com.thisatmind.appingpot.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by patrick on 2016-11-10.
 */

public class User  extends RealmObject {

    private String firebaseToken;
    private String key;
    private String userType;
    private String facebookToken;

    public User() {}

    public User(String key, String firebaseToken, String userType, String facebookToken) {
        this.key = key;
        this.firebaseToken = firebaseToken;
        this.userType = userType;
        this.facebookToken = facebookToken;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }
}
