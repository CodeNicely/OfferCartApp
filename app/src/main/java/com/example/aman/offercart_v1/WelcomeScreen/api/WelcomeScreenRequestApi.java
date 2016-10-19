package com.example.aman.offercart_v1.WelcomeScreen.api;

import com.example.aman.offercart_v1.WelcomeScreen.models.data.WelcomeScreenData;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aman on 16/10/16.
 */
public interface WelcomeScreenRequestApi {

    @GET(Urls.REQUEST_Welcome_SCREEN)
    Call<WelcomeScreenData> getWelcomeData();

}
