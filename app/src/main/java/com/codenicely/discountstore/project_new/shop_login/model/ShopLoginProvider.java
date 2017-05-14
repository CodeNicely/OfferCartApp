package com.codenicely.discountstore.project_new.shop_login.model;

import com.codenicely.discountstore.project_new.shop_login.ShopLoginCallback;

/**
 * Created by ujjwal on 15/5/17.
 */
public interface ShopLoginProvider {

	void getLoginDetails(String mobile, String password,ShopLoginCallback shopLoginCallback);

}
