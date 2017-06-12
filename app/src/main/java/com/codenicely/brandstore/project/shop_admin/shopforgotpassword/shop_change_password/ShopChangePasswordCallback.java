package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password;

import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.data.ShopChangePasswordData;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopChangePasswordCallback {
	void onSuccess(ShopChangePasswordData shopChangePasswordData);
	void onFailure(String error);
}
