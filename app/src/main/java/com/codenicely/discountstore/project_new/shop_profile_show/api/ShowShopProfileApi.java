package com.codenicely.discountstore.project_new.shop_profile_show.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_profile_show.data.ShowShopProfileData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ujjwal on 16/5/17.
 */
public interface ShowShopProfileApi {

	@GET(Urls.REQUEST_SHOP_PROFILE)
	Call<ShowShopProfileData> getShopProfileData(@Query("access_token") String access_token);

}
