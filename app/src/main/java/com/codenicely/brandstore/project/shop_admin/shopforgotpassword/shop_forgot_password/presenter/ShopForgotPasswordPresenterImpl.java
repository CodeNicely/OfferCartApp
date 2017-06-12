package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.presenter;

import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.ShopForgotPasswordCallback;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.data.ShopForgotPasswordData;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.model.ShopForgotPasswordHelper;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.view.ShopForgotPasswordView;

/**
 * Created by ujjwal on 1/6/17.
 */
public class ShopForgotPasswordPresenterImpl implements ShopForgotPasswordPresenter {
	ShopForgotPasswordView  shopForgotPasswordView;
	ShopForgotPasswordHelper shopForgotPasswordHelper;

	public ShopForgotPasswordPresenterImpl(ShopForgotPasswordView shopForgotPasswordView, ShopForgotPasswordHelper shopForgotPasswordHelper) {
		this.shopForgotPasswordView = shopForgotPasswordView;
		this.shopForgotPasswordHelper = shopForgotPasswordHelper;
	}


	@Override
	public void getMoblieNumber(String mobile) {
		shopForgotPasswordView.showProgressbar(true);
		shopForgotPasswordHelper.getMobileNumber(mobile, new ShopForgotPasswordCallback() {
			@Override
			public void onSuccess(ShopForgotPasswordData shopForgotPasswordData) {
				shopForgotPasswordView.showProgressbar(false);
				if (shopForgotPasswordData.isSuccess()){
				shopForgotPasswordView.onOTPSent();
				shopForgotPasswordView.showMessage(shopForgotPasswordData.getMessage());
				}else {
					shopForgotPasswordView.showMessage(shopForgotPasswordData.getMessage());
				}
			}
			@Override
			public void onFailure(String error) {
				shopForgotPasswordView.showMessage(error);
			}
		});

	}
}
