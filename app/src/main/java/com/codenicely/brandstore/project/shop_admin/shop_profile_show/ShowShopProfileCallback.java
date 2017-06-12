package com.codenicely.brandstore.project.shop_admin.shop_profile_show;

import com.codenicely.brandstore.project.shop_admin.shop_profile_show.data.ShowShopProfileData;

/**
 * Created by ujjwal on 16/5/17.
 */
public interface ShowShopProfileCallback {
	void onSuccess(ShowShopProfileData shopProfileData);
	void onFailure();
}
