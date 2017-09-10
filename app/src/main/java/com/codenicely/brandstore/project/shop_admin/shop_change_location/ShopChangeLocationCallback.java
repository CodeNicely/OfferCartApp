package com.codenicely.brandstore.project.shop_admin.shop_change_location;

import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopChangeLocationData;

/**
 * Created by ujjwal on 9/9/17.
 */

public interface ShopChangeLocationCallback {
	void onSuccess(ShopChangeLocationData shopChangeLocationData);
	void onFailure(String error);
}
