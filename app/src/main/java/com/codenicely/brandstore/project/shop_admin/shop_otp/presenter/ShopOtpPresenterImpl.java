package com.codenicely.brandstore.project.shop_admin.shop_otp.presenter;

import com.codenicely.brandstore.project.shop_admin.shop_otp.ShopOtpCallback;
import com.codenicely.brandstore.project.shop_admin.shop_otp.data.ShopOtpData;
import com.codenicely.brandstore.project.shop_admin.shop_otp.model.ShopOtpProvider;
import com.codenicely.brandstore.project.shop_admin.shop_otp.view.ShopOtpView;

/**
 * Created by ujjwal on 14/5/17.
 */
public class ShopOtpPresenterImpl implements ShopOtpPresenter {

	ShopOtpView shopOtpView;
	ShopOtpProvider shopOtpProvider;

	public ShopOtpPresenterImpl(ShopOtpView shopOtpView, ShopOtpProvider shopOtpProvider) {
		this.shopOtpView = shopOtpView;
		this.shopOtpProvider = shopOtpProvider;
	}

	@Override
	public void requestOtp(String otp, String mobile) {
		shopOtpView.showProgressbar(true);
		shopOtpProvider.requestOtp(otp,mobile, new ShopOtpCallback() {
			@Override
			public void onSuccess(ShopOtpData shopOtpData) {
				if (shopOtpData.isSuccess()){
					shopOtpView.showProgressbar(false);
					shopOtpView.onOtpVerified(shopOtpData.getAccess_token());


				}else {
					shopOtpView.showProgressbar(false);
					shopOtpView.showMessage(shopOtpData.getMessage());
				}
			}

			@Override
			public void onFailure(String error) {
				shopOtpView.showProgressbar(false);
				shopOtpView.showMessage("Failed");
			}
		});
	}
}
