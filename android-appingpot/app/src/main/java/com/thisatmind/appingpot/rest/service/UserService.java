package com.thisatmind.appingpot.rest.service;

import com.thisatmind.appingpot.rest.model.ResultInfo;
import com.thisatmind.appingpot.rest.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by mac on 26/10/2016.
 */

public interface UserService {

    @POST("user/signup")
    Call<ResultInfo> addUser(@Body UserInfo userInfo);

    @PUT("user/firebaseDeviceToken")
    Call<ResultInfo> putFirebaseDeviceToken(@Body ResultInfo resultInfo);
}
