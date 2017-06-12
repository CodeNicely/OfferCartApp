package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.view;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopForgotPasswordView {
	void showMessage(String message);
	void showProgressbar(boolean show);
	void onOTPSent();
}
