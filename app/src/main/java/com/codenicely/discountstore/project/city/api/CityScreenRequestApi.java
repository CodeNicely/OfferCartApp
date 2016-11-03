package com.codenicely.discountstore.project.city.api;

import com.codenicely.discountstore.project.city.view.Response;
import com.codenicely.discountstore.project.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenRequestApi {
    @GET(Urls.REQUEST_CITY)
    Call<Response> getCities(@Query("access_token") String token);

}
