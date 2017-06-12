package com.codenicely.brandstore.project.shop_admin.shop_otp.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_otp.data.ShopOtpData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 14/5/17.
 */
public interface ShopOtpApi {
	@FormUrlEncoded
	@POST(Urls.VERIFY_SHOP_OTP)
	Call<ShopOtpData> requestOtp(@Field("otp") String otp, @Field("mobile") String mobile);
}
