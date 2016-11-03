package com.codenicely.discountstore.project.welcome_screen.api;

import com.codenicely.discountstore.project.helper.Urls;
import com.codenicely.discountstore.project.welcome_screen.models.data.WelcomeScreenData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aman on 16/10/16.
 */
public interface WelcomeScreenRequestApi {

    @GET(Urls.REQUEST_Welcome_SCREEN)
    Call<WelcomeScreenData> getWelcomeData();

}
