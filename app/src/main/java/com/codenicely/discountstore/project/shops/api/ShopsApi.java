package com.codenicely.discountstore.project.shops.api;

import com.codenicely.discountstore.project.helper.Urls;
import com.codenicely.discountstore.project.shops.model.data.ShopList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iket on 22/10/16.
 */
public interface ShopsApi {
    @GET(Urls.RequestShop)
    Call<ShopList> getShops(@Query("access_token") String access_token, @Query("category_id") String category_id);
}
