package com.thisatmind.appingpot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.thisatmind.appingpot.R;
import com.thisatmind.appingpot.models.RecoCard;

import io.realm.Realm;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Handler hd = new Handler();
        hd.postDelayed(new Splashhandler() , 2000);

        // if this activity started by fcm message
        if(getIntent().getExtras() != null){
            fcmJob(getIntent().getExtras());
        }
    }

    private void fcmJob(final Bundle data){

        Log.d("fcmJob", data.toString());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RecoCard card = realm.createObject(RecoCard.class);
                    card.setKey(data.getString("id"));
                    card.setPackageName(data.getString("packageName"));
                    card.setIcon(data.getString("icon"));
                    card.setMarketUrl(data.getString("marketUrl"));
                    card.setTitle(data.getString("title"));
                    card.setUserId(data.getString("userId"));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("TEST", e.toString());
                    Log.d("TEST", "exception here : FCM dao");
                }
            }
        });

    }

    private class Splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(), LoginActivity.class));
            SplashActivity.this.finish();
        }
    }
}
