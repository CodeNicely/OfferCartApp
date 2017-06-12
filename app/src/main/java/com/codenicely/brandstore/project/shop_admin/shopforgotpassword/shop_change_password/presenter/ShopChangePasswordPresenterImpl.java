package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.presenter;

import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.ShopChangePasswordCallback;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.data.ShopChangePasswordData;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.model.ShopChangePasswordHelper;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.view.ShopChangePasswordView;

/**
 * Created by ujjwal on 17/5/17.
 */
public class ShopChangePasswordPresenterImpl implements ShopChangePasswordPresenter {
	private ShopChangePasswordView shopChangePasswordView;
	private ShopChangePasswordHelper shopChangePasswordHelper;


	public ShopChangePasswordPresenterImpl(ShopChangePasswordView shopChangePasswordView, ShopChangePasswordHelper shopChangePasswordHelper) {
		this.shopChangePasswordView = shopChangePasswordView;
		this.shopChangePasswordHelper = shopChangePasswordHelper;
	}

	@Override
	public void requestChangePassword(String shop_access_token, String new_password) {
		shopChangePasswordView.showProgressbar(true);
		shopChangePasswordHelper.requestChangePassword(shop_access_token,new_password, new ShopChangePasswordCallback() {
			@Override
			public void onSuccess(ShopChangePasswordData shopChangePasswordData) {
				if (shopChangePasswordData.isSuccess()){
					shopChangePasswordView.showProgressbar(false);
					shopChangePasswordView.showMessage(shopChangePasswordData.getMessage());
					shopChangePasswordView.onPasswordChanged();
					}else {
					shopChangePasswordView.showProgressbar(false);
					shopChangePasswordView.showMessage(shopChangePasswordData.getMessage());

				}
			}

			@Override
			public void onFailure(String error) {
				shopChangePasswordView.showProgressbar(false);
				shopChangePasswordView.showMessage(error);
			}
		});
	}
}
