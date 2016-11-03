package com.example.aman.offercart_v1.developers.api;


import com.example.aman.offercart_v1.developers.model.data.DeveloperData;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersApi {

    @GET(Urls.SUB_URL_DEVELOPERS)
    Call<DeveloperData> requestDeveloperData();

}
