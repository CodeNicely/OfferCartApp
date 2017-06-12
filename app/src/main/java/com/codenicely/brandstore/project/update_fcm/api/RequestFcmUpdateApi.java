package com.codenicely.brandstore.project.update_fcm.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.update_fcm.model.data.FcmUpdateData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ramya on 7/3/17.
 */

public interface RequestFcmUpdateApi {
    @FormUrlEncoded
    @POST(Urls.REQUEST_FCM_UPDATE)
    Call<FcmUpdateData> sendFcmUpdateRequest(@Field("access_token") String access_token,@Field("fcm") String fcm);
}
