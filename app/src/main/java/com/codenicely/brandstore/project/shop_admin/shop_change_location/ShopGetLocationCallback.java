package com.codenicely.brandstore.project.shop_admin.shop_change_location;

import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopGetLocationData;

/**
 * Created by ujjwal on 9/9/17.
 */

public interface ShopGetLocationCallback {
	void onSuccess(ShopGetLocationData shopGetLocationData );
	void onFailure();
}
