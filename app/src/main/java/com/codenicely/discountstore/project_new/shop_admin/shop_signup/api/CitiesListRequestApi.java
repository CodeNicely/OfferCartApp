package com.codenicely.discountstore.project_new.shop_admin.shop_signup.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data.CitiesData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ramya on 26/2/17.
 */

public interface CitiesListRequestApi {
    @GET(Urls.REQUEST_CITY)
    Call<CitiesData> requestCityList(@Query("access_token") String access_token);
}
