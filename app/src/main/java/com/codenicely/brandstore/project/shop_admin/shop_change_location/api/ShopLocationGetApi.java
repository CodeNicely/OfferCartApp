package com.codenicely.brandstore.project.shop_admin.shop_change_location.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopChangeLocationData;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopGetLocationData;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ujjwal on 9/9/17.
 */

public interface ShopLocationGetApi {
	@GET(Urls.SHOP_GET_LOCATION)
	Call<ShopGetLocationData> requestLocation(@Query("shop_access_token") String shop_access_token);
}
