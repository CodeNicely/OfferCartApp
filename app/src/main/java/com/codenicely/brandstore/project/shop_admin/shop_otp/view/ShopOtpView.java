package com.codenicely.brandstore.project.shop_admin.shop_otp.view;

/**
 * Created by ujjwal on 14/5/17.
 */
public interface ShopOtpView {
	void showMessage(String message);
	void showProgressbar(boolean show);
	void onOtpVerified(String access_token);
}
