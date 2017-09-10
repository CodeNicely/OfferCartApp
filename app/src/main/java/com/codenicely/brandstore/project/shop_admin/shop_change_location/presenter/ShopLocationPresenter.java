package com.codenicely.brandstore.project.shop_admin.shop_change_location.presenter;


/**
 * Created by ujjwal on 7/9/17.
 */

public interface ShopLocationPresenter {
	void requestShopLocation(String access_token);
	void requestChangeLocation(String shop_access_token, Double latitude, Double longitude);

}
