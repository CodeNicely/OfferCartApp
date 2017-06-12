package com.codenicely.brandstore.project.shop_admin.shop_profile_show.presenter;

import com.codenicely.brandstore.project.shop_admin.shop_profile_show.ShowShopProfileCallback;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.data.ShowShopProfileData;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.model.ShowShopProfileProvider;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.view.ShowShopProfileView;

/**
 * Created by ujjwal on 17/5/17.
 */
public class ShowShopProfilePresenterImpl implements ShowShopProfilePresenter {

	ShowShopProfileView shopProfileView;
	ShowShopProfileProvider shopProfileProvider;

	public ShowShopProfilePresenterImpl(ShowShopProfileView shopProfileView, ShowShopProfileProvider shopProfileProvider) {
		this.shopProfileView = shopProfileView;
		this.shopProfileProvider = shopProfileProvider;
	}

	@Override
	public void requestShopProfileDetails(String access_token) {
		shopProfileView.showProgressbar(true);
		shopProfileProvider.requestShopProfileDetails(access_token, new ShowShopProfileCallback() {

			@Override
			public void onSuccess(ShowShopProfileData shopProfileData) {
				shopProfileView.showProgressbar(false);
				shopProfileView.onProfileRecieved(shopProfileData);
			}

			@Override
			public void onFailure() {
				shopProfileView.showProgressbar(false);
				shopProfileView.showMessage("Something went wrong");
			}
		});

	}
}
