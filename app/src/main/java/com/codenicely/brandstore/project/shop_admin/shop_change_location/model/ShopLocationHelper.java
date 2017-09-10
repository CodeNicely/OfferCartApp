package com.codenicely.brandstore.project.shop_admin.shop_change_location.model;

import com.codenicely.brandstore.project.shop_admin.shop_change_location.ShopChangeLocationCallback;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.ShopGetLocationCallback;
import com.codenicely.brandstore.project.shop_admin.shop_change_password.ShopChangePasswordCallback;

/**
 * Created by ujjwal on 7/9/17.
 */

public interface ShopLocationHelper {
	void requestShopLocation(String access_token,ShopGetLocationCallback shopGetLocationCallback);
	void requestChangeLocation(String shop_access_token, Double latitude, Double longitude, ShopChangeLocationCallback shopChangeLocationCallback);
}
