package com.thisatmind.appingpot.rest.model;

/**
 * Created by patrick on 2016-11-13.
 */

public class RecoResult {

    String recoId;
    String result;

    public RecoResult() {}

    public RecoResult(String recoId, String result) {
        this.recoId = recoId;
        this.result = result;
    }

    public String getRecoId() {
        return recoId;
    }

    public void setRecoId(String recoId) {
        this.recoId = recoId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
