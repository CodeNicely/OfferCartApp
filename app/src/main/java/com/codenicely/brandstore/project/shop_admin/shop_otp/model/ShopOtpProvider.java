package com.codenicely.brandstore.project.shop_admin.shop_otp.model;


import com.codenicely.brandstore.project.shop_admin.shop_otp.ShopOtpCallback;

/**
 * Created by ujjwal on 14/5/17.
 */
public interface ShopOtpProvider {
	void requestOtp(String otp,String mobile, ShopOtpCallback shopOtpCallback);
}
