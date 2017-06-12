package com.codenicely.brandstore.project.shop_admin.shop_otp;

import com.codenicely.brandstore.project.shop_admin.shop_otp.data.ShopOtpData;

/**
 * Created by ujjwal on 14/5/17.
 */
public interface ShopOtpCallback {

	void onSuccess(ShopOtpData shopLoginData);

	void onFailure(String error);

}
