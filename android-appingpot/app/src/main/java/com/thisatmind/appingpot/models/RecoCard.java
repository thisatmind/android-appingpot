package com.thisatmind.appingpot.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by patrick on 2016-11-13.
 */

public class RecoCard extends RealmObject {

        @PrimaryKey
        @Required
        private String key;
        private String packageName;
        private String title;
        private String icon;
        private String marketUrl;

        public RecoCard(){}
        public RecoCard(String key, String packageName, String title, String icon, String marketUrl) {
                this.key = key;
                this.packageName = packageName;
                this.title = title;
                this.icon = icon;
                this.marketUrl = marketUrl;
        }

        public String getKey() {
                return key;
        }

        public void setKey(String key) {
                this.key = key;
        }

        public String getPackageName() {
                return packageName;
        }

        public void setPackageName(String packageName) {
                this.packageName = packageName;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getIcon() {
                return icon;
        }

        public void setIcon(String icon) {
                this.icon = icon;
        }

        public String getMarketUrl() {
                return marketUrl;
        }

        public void setMarketUrl(String marketUrl) {
                this.marketUrl = marketUrl;
        }
}
