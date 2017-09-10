package com.codenicely.brandstore.project.shop_admin.shop_change_password.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_change_password.data.ShopChangePasswordData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopAdminChangePasswordApi {
	@FormUrlEncoded
	@POST(Urls.SHOP_CHANGE_OLD_PASSWORD)
	Call<ShopChangePasswordData> requestChangePassword(@Field("shop_access_token") String shop_access_token,
	@Field("old_password") String old_password,@Field("new_password") String new_password);
}
