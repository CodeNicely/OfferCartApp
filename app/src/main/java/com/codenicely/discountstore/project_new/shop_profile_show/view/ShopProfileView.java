package com.codenicely.discountstore.project_new.shop_profile_show.view;

import com.codenicely.discountstore.project_new.shop_profile_show.data.ShowShopProfileData;

/**
 * Created by ujjwal on 16/5/17.
 */
public interface ShopProfileView {
	void showProgressbar(boolean show);
	void showMessage(String message);
	void onProfileRecieved(ShowShopProfileData shopProfileData);
}
