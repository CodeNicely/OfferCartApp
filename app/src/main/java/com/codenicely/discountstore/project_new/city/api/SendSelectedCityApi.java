package com.codenicely.discountstore.project_new.city.api;

import com.codenicely.discountstore.project_new.city.data.SelectedCityData;
import com.codenicely.discountstore.project_new.helper.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by iket on 19/10/16.
 */
public interface SendSelectedCityApi {
    @FormUrlEncoded
    @POST(Urls.SEND_CITY)
    Call<SelectedCityData> sendCity(@Field("city_id") int city_id, @Field("access_token") String token);
}
