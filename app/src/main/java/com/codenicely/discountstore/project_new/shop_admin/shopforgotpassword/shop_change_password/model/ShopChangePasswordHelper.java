package com.codenicely.discountstore.project_new.shop_admin.shopforgotpassword.shop_change_password.model;

import com.codenicely.discountstore.project_new.shop_admin.shopforgotpassword.shop_change_password.ShopChangePasswordCallback;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopChangePasswordHelper {
	void requestChangePassword(String shop_access_token, String new_password, ShopChangePasswordCallback shopChangePasswordCallback);

}
