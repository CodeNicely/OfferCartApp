package com.codenicely.discountstore.project_new.shop_admin.shop_change_password.model;

import com.codenicely.discountstore.project_new.shop_admin.shop_change_password.ShopChangePasswordCallback;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopChangePasswordHelper {
	void requestChangePassword(String shop_access_token, String old_password, String new_password, ShopChangePasswordCallback shopChangePasswordCallback);

}
