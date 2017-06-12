package com.codenicely.brandstore.project.shop_admin.shop_profile_show.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.data.ShowShopProfileData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ujjwal on 16/5/17.
 */
public interface ShowShopProfileApi {

	@GET(Urls.REQUEST_SHOP_PROFILE)
	Call<ShowShopProfileData> getShopProfileData(@Query("shop_access_token") String access_token);

}
