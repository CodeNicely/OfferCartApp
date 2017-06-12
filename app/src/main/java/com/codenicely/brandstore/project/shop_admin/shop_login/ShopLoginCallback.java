package com.codenicely.brandstore.project.shop_admin.shop_login;

import com.codenicely.brandstore.project.shop_admin.shop_login.data.ShopLoginData;

/**
 * Created by ujjwal on 15/5/17.
 */
public interface ShopLoginCallback {
	void onSuccess(ShopLoginData shopLoginData);
	void onFailure(String error);
}
