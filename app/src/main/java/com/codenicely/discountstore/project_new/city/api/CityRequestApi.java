package com.codenicely.discountstore.project_new.city.api;

import com.codenicely.discountstore.project_new.city.data.CityData;
import com.codenicely.discountstore.project_new.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 15/10/16.
 */
public interface CityRequestApi {
    @GET(Urls.REQUEST_CITY)
    Call<CityData> getCities(@Query("access_token") String token,@Query("state_id") int state_id );

}
