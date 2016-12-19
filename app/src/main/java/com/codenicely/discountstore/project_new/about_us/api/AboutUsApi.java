package com.codenicely.discountstore.project_new.about_us.api;


import com.codenicely.discountstore.project_new.about_us.model.data.AboutUsData;
import com.codenicely.discountstore.project_new.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsApi {


    @GET(Urls.SUB_URL_ABOUT_US)
    Call<AboutUsData> getAboutUsData();


}
