package com.example.aman.offercart_v1.SplashScreen.api;

import com.example.aman.offercart_v1.SplashScreen.models.data.SplashScreenData;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.GET;

/**
 * Created by aman on 14/10/16.
 */
public interface SplashScreenRequestApi {

    @GET(Urls.REQUEST_SPLASH_SCREEN)
    Call<SplashScreenData>requestSplash(@Field("version")int version,@Field("success")boolean sucess,
                                        @Field("message")String message,
                                        @Field("compulsory_update")int compulsory_update);
}
