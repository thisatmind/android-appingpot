package com.thisatmind.appingpot.fragment.pojo;

/**
 * Created by patrick on 2016-10-27.
 */

public class RecoCard {

    private String userId;
    private String recoId;
    private String appName;
    private String appImg;
    private String installLink;

    public RecoCard() {}

    public RecoCard(String userId, String recoId, String appName, String appImg, String installLink) {
        this.userId = userId;
        this.recoId = recoId;
        this.appName = appName;
        this.appImg = appImg;
        this.installLink = installLink;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecoId() {
        return recoId;
    }

    public void setRecoId(String recoId) {
        this.recoId = recoId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppImg() {
        return appImg;
    }

    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }

    public String getInstallLink(){return this.installLink;}

    public void setInstallLink(String installLink){this.installLink = installLink;}
}
