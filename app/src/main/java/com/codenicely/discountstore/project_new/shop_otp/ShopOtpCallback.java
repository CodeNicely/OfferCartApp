package com.codenicely.discountstore.project_new.shop_otp;

import com.codenicely.discountstore.project_new.shop_otp.data.ShopOtpData;

/**
 * Created by ujjwal on 14/5/17.
 */
public interface ShopOtpCallback {

	void onSuccess(ShopOtpData shopLoginData);

	void onFailure(String error);

}
