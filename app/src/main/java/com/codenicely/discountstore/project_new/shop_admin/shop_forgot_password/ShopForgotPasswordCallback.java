package com.codenicely.discountstore.project_new.shop_admin.shop_forgot_password;

import com.codenicely.discountstore.project_new.shop_admin.shop_forgot_password.data.ShopForgotPasswordData;

/**
 * Created by ujjwal on 1/6/17.
 */
public interface ShopForgotPasswordCallback {
	void onSuccess(ShopForgotPasswordData shopForgotPasswordData);
	void onFailure(String error);
}
