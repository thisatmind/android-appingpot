package com.thisatmind.appingpot.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.thisatmind.appingpot.rest.RestClient;
import com.thisatmind.appingpot.rest.model.DeviceToken;
import com.thisatmind.appingpot.rest.model.ResultInfo;
import com.thisatmind.appingpot.rest.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by patrick on 2016-11-11.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    private static final String TAG = "MyFirebaseIIDService";


    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {

        String userId = null;
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        String deviceToken = token;
        if(userId != null){
            UserService userService = RestClient.createService(UserService.class);
            Call<ResultInfo> call = userService.putDeviceToken(new DeviceToken(userId, deviceToken));
            call.enqueue(new Callback<ResultInfo>(){
                @Override
                public void onResponse(Call<ResultInfo> call, Response<ResultInfo> response) {
                    Log.d(TAG, "refresh firebase device token success : ");
                }

                @Override
                public void onFailure(Call<ResultInfo> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}
