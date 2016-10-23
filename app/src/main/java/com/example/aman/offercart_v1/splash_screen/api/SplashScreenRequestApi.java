package com.example.aman.offercart_v1.splash_screen.api;

import com.example.aman.offercart_v1.splash_screen.models.data.SplashScreenData;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 14/10/16.
 */
public interface SplashScreenRequestApi {


    @GET(Urls.REQUEST_SPLASH_SCREEN)
    Call<SplashScreenData> requestSplash();
}
