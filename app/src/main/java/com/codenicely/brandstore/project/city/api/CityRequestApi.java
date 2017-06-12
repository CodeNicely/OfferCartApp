package com.codenicely.brandstore.project.city.api;

import com.codenicely.brandstore.project.city.data.CityData;
import com.codenicely.brandstore.project.helper.Urls;

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
