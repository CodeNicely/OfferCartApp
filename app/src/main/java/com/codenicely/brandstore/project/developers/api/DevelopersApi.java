package com.codenicely.brandstore.project.developers.api;


import com.codenicely.brandstore.project.developers.model.data.DeveloperData;
import com.codenicely.brandstore.project.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersApi {

    @GET(Urls.SUB_URL_DEVELOPERS)
    Call<DeveloperData> requestDeveloperData();

}
