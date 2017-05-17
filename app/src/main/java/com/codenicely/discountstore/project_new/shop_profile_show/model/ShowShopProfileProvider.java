package com.codenicely.discountstore.project_new.shop_profile_show.model;

import com.codenicely.discountstore.project_new.shop_profile_show.ShowShopProfileCallback;

/**
 * Created by ujjwal on 16/5/17.
 */
public interface ShowShopProfileProvider {
	void requestShopProfileDetails(String access_token, ShowShopProfileCallback shopProfileCallback);
}
