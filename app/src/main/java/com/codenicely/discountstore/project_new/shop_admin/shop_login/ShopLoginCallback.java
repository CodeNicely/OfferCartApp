package com.codenicely.discountstore.project_new.shop_admin.shop_login;

import com.codenicely.discountstore.project_new.shop_admin.shop_login.data.ShopLoginData;

/**
 * Created by ujjwal on 15/5/17.
 */
public interface ShopLoginCallback {
	void onSuccess(ShopLoginData shopLoginData);
	void onFailure(String error);
}
