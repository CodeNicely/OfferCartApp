package com.example.aman.offercart_v1.city.api;

import com.example.aman.offercart_v1.city.view.Response;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenRequestApi {
    @GET(Urls.REQUEST_CITY)
    Call<Response>getCities(@Query("access_token")String token);

}
