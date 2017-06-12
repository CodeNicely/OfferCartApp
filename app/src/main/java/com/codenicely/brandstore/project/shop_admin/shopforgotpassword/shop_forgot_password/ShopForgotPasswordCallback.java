package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password;

import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.data.ShopForgotPasswordData;

/**
 * Created by ujjwal on 1/6/17.
 */
public interface ShopForgotPasswordCallback {
	void onSuccess(ShopForgotPasswordData shopForgotPasswordData);
	void onFailure(String error);
}
