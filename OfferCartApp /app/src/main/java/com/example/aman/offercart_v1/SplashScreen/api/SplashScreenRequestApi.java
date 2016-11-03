package com.codenicely.discountstore.project.splash_screen.api;

import com.codenicely.discountstore.project.splash_screen.models.data.SplashScreenData;
import com.codenicely.discountstore.project.helper.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by aman on 14/10/16.
 */
public interface SplashScreenRequestApi {


    @GET(Urls.REQUEST_SPLASH_SCREEN)
    Call<SplashScreenData> requestSplash();
}
