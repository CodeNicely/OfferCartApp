package com.example.aman.offercart_v1.city.api;

import com.example.aman.offercart_v1.city.models.data.SelectedCityData;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by iket on 19/10/16.
 */
public interface SendSelectedCityApi {
    @FormUrlEncoded
    @POST(Urls.Send_CITY)
    Call<SelectedCityData>sendCity(@Field("city_id") String city_id,@Field("access_token") String token);

}
