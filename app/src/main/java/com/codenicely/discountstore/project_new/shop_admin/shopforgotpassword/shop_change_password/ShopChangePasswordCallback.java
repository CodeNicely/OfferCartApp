package com.codenicely.discountstore.project_new.shop_admin.shopforgotpassword.shop_change_password;

import com.codenicely.discountstore.project_new.shop_admin.shopforgotpassword.shop_change_password.data.ShopChangePasswordData;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopChangePasswordCallback {
	void onSuccess(ShopChangePasswordData shopChangePasswordData);
	void onFailure(String error);
}
