package com.codenicely.discountstore.project_new.shop_otp.model;


import com.codenicely.discountstore.project_new.shop_otp.ShopOtpCallback;

/**
 * Created by ujjwal on 14/5/17.
 */
public interface ShopOtpProvider {
	void requestOtp(String otp,String mobile, ShopOtpCallback shopOtpCallback);
}
