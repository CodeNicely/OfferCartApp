package com.example.aman.offercart_v1.about_us.api;


import com.example.aman.offercart_v1.about_us.model.data.AboutUsData;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsApi {


    @GET(Urls.SUB_URL_ABOUT_US)
    Call<AboutUsData> getAboutUsData();


}
