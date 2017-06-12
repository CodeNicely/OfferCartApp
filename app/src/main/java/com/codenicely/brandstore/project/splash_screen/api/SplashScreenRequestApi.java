package com.codenicely.brandstore.project.splash_screen.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.splash_screen.models.data.SplashScreenData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 14/10/16.
 */
public interface SplashScreenRequestApi {


    @GET(Urls.REQUEST_SPLASH_SCREEN)
    Call<SplashScreenData> requestSplash(@Query("fcm") String fcm);
}
