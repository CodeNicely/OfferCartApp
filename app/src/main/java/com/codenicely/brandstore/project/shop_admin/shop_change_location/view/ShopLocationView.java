package com.codenicely.brandstore.project.shop_admin.shop_change_location.view;

import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopGetLocationData;

/**
 * Created by ujjwal on 7/9/17.
 */

public interface ShopLocationView {
	void showProgressbar(boolean show);
	void showMessage(String message);
	void onShopLocationChanged();
	void onLocationRecieved(ShopGetLocationData shopGetLocationData);
}
