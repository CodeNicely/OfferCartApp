package com.example.aman.offercart_v1.shopcategories.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.shopcategories.models.data.ShopCategoryData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by aman on 19/10/16.
 */

public interface ShopCategoryRequestApi {

    @GET(Urls.REQUEST_CITY)
    Call<ShopCategoryData> getShopCategoryData();

    @FormUrlEncoded
    @POST(Urls.REQUEST_CITY)
    Call<ShopCategoryData> getShops(@Field("subcategory_name") String subcategory_name);

}
