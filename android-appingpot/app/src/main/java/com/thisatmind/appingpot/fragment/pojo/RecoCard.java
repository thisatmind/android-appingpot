package com.thisatmind.appingpot.fragment.pojo;

/**
 * Created by patrick on 2016-10-27.
 */

public class RecoCard {

    private String appName;
    private String appImg;
    private String installLink;

    public RecoCard() {}

    public RecoCard(String appName, String appImg, String installLink) {
        this.appName = appName;
        this.appImg = appImg;
        this.installLink = installLink;
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
