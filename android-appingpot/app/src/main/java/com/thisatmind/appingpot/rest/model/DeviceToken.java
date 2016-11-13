package com.thisatmind.appingpot.rest.model;

/**
 * Created by patrick on 2016-11-11.
 */

public class DeviceToken {

    String userId;
    String deviceId;

    public DeviceToken(){}
    public DeviceToken(String userId, String deviceId) {
        this.userId = userId;
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
