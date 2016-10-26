package com.thisatmind.appingpot.rest.service;

import com.thisatmind.appingpot.rest.model.ResultInfo;
import com.thisatmind.appingpot.rest.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by mac on 26/10/2016.
 */

public interface UserService {

    @POST("user/signin")
    Call<ResultInfo> addUser(@Body UserInfo userInfo);

}
