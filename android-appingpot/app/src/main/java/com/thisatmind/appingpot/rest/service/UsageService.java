package com.thisatmind.appingpot.rest.service;

import com.thisatmind.appingpot.rest.model.ResultInfo;
import com.thisatmind.appingpot.rest.model.UsageList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by patrick on 2016-11-10.
 */

public interface UsageService {

    @POST("usage")
    Call<ResultInfo> addUsageList(@Body UsageList usageList);
}
