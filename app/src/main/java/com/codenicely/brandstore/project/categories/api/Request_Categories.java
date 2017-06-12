package com.codenicely.brandstore.project.categories.api;

import com.codenicely.brandstore.project.categories.model.data.CategoriesList;
import com.codenicely.brandstore.project.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iket on 19/10/16.
 */
public interface Request_Categories {
    @GET(Urls.CATEGORIES)
    Call<CategoriesList> getCategories(@Query("access_token") String access_token);
}
