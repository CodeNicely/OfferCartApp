package com.example.aman.offercart_v1.cityScreen.api;

import com.example.aman.offercart_v1.cityScreen.view.Response;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenRequestApi {
    @GET(Urls.REQUEST_CITY)
    Call<Response>getCities();

}
