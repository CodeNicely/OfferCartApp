package com.codenicely.brandstore.project.shop_admin.shop_change_location.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopChangeLocationData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ujjwal on 9/9/17.
 */

public interface ShopChangeLocationApi {
	@GET(Urls.SHOP_CHANGE_LOCATION)
	Call<ShopChangeLocationData> requestChangeLocation(@Query("shop_access_token") String shop_access_token,
    @Query("latitude") Double latitude, @Query("longitude") Double longitude);
}
