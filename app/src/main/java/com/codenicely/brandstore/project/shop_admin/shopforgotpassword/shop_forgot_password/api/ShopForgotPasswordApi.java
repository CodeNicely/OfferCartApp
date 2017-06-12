package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.data.ShopForgotPasswordData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 1/6/17.
 */

public interface ShopForgotPasswordApi {
	@FormUrlEncoded
	@POST(Urls.SHOP_FORGOT_PASSWORD)
	Call<ShopForgotPasswordData> requestNewPassword(@Field("mobile") String mobile);
}
