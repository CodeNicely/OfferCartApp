package com.codenicely.discountstore.project_new.shop_change_password.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_change_password.data.ShopChangePasswordData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopAdminChangePasswordApi {
	@FormUrlEncoded
	@POST(Urls.VERIFY_SHOP_LOGIN)
	Call<ShopChangePasswordData> requestChangePassword(@Field("shop_access_token") String shop_access_token,
			@Field("old_password") String old_password,@Field("new_password") String new_password);
}
