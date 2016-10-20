package com.example.aman.offercart_v1.home.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.home.view.CategoriesList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by iket on 19/10/16.
 */
public interface Request_Categories {
    @GET(Urls.CATEGORIES)
    Call<CategoriesList>getCategories();
}
