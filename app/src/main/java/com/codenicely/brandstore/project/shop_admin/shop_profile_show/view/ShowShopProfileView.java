package com.codenicely.brandstore.project.shop_admin.shop_profile_show.view;

import com.codenicely.brandstore.project.shop_admin.shop_profile_show.data.ShowShopProfileData;

/**
 * Created by ujjwal on 16/5/17.
 */
public interface ShowShopProfileView {
	void showProgressbar(boolean show);
	void showMessage(String message);
	void onProfileRecieved(ShowShopProfileData shopProfileData);
}
