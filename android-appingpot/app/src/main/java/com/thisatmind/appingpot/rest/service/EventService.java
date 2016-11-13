package com.thisatmind.appingpot.rest.service;

import com.thisatmind.appingpot.rest.model.AppInfo;
import com.thisatmind.appingpot.rest.model.EventList;
import com.thisatmind.appingpot.rest.model.ResultInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by patrick on 2016-10-02.
 */

public interface EventService {

    @GET("app")
    Call<AppInfo> getAppInfo();

    @POST("app")
    Call<AppInfo> addAppInfo(@Body AppInfo appInfo);

    @POST("count")
    Call<ResultInfo> addEventList(@Body EventList eventList);
}
