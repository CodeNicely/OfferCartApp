package com.codenicely.discountstore.project_new.shop_login.presenter;

import com.codenicely.discountstore.project_new.shop_login.ShopLoginCallback;
import com.codenicely.discountstore.project_new.shop_login.data.ShopLoginData;
import com.codenicely.discountstore.project_new.shop_login.model.RetrofitShopLoginProvider;
import com.codenicely.discountstore.project_new.shop_login.model.ShopLoginProvider;
import com.codenicely.discountstore.project_new.shop_login.view.ShopLoginFragment;
import com.codenicely.discountstore.project_new.shop_login.view.ShopLoginView;

/**
 * Created by ujjwal on 15/5/17.
 */
public class ShopLoginPresenterImpl implements ShopLoginPresenter {
	ShopLoginView shopLoginView;

	ShopLoginProvider shopLoginProvider;

	public ShopLoginPresenterImpl(ShopLoginView shopLoginView, ShopLoginProvider shopLoginProvider) {
		this.shopLoginView = shopLoginView;
		this.shopLoginProvider = shopLoginProvider;
	}

	@Override
	public void getLoginDetails(String mobile, String password) {

		shopLoginView.showProgressbar(true);
		shopLoginProvider.getLoginDetails(mobile, password, new ShopLoginCallback() {
			@Override
			public void onSuccess(ShopLoginData shopLoginData) {
				if (shopLoginData.isSuccess()) {
					shopLoginView.showProgressbar(false);
					shopLoginView.onLoginVerified(shopLoginData.getAccess_token());
				} else {
					shopLoginView.showProgressbar(false);
					shopLoginView.showMessage(shopLoginData.getMessage());
				}
			}

			@Override
			public void onFailure(String error) {
				shopLoginView.showProgressbar(false);
				shopLoginView.showMessage(error);
			}
		});
	}
}
