package com.thisatmind.appingpot.rest.service;

import com.thisatmind.appingpot.rest.model.RecoResult;
import com.thisatmind.appingpot.rest.model.ResultInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by patrick on 2016-11-13.
 */

public interface RecoService {

    @POST("reco")
    Call<ResultInfo> addRecoResult(@Body RecoResult result);

}
