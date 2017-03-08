package com.codenicely.discountstore.project_new.categories.api;

import com.codenicely.discountstore.project_new.categories.model.data.CategoriesList;
import com.codenicely.discountstore.project_new.helper.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by iket on 19/10/16.
 */
public interface Request_Categories {
    @GET(Urls.CATEGORIES)
    Call<CategoriesList> getCategories(@Query("access_token") String access_token);
}
