package com.thisatmind.appingpot.rest.model;

/**
 * Created by mac on 26/10/2016.
 */

public class ResultInfo {

    String message;

    public ResultInfo() {}

    public ResultInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
