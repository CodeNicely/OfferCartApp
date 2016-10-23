package com.example.aman.offercart_v1.shops.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.shops.model.data.ShopList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iket on 22/10/16.
 */
public interface ShopsApi {
    @GET(Urls.RequestShop)
    Call<ShopList>getShops(@Query("category_id")String category_id);
}
