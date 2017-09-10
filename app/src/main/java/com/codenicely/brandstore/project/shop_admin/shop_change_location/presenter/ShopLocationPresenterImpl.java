package com.codenicely.brandstore.project.shop_admin.shop_change_location.presenter;

import com.codenicely.brandstore.project.shop_admin.shop_change_location.ShopChangeLocationCallback;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.ShopGetLocationCallback;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopChangeLocationData;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopGetLocationData;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.model.ShopLocationHelper;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.view.ShopLocationView;

/**
 * Created by ujjwal on 7/9/17.
 */

public class ShopLocationPresenterImpl implements  ShopLocationPresenter {
	private ShopLocationView shopLocationView;
	private ShopLocationHelper shopLocationHelper;

	public ShopLocationPresenterImpl(ShopLocationView shopLocationView, ShopLocationHelper shopLocationHelper) {
		this.shopLocationView = shopLocationView;
		this.shopLocationHelper = shopLocationHelper;
	}

	@Override
	public void requestShopLocation(String access_token) {
		shopLocationView.showProgressbar(true);
		shopLocationHelper.requestShopLocation(access_token, new ShopGetLocationCallback() {
			@Override
			public void onSuccess(ShopGetLocationData shopGetLocationData) {
				shopLocationView.showProgressbar(false);
				if(shopGetLocationData.isSuccess()){
					shopLocationView.onLocationRecieved(shopGetLocationData);
				}else {
					shopLocationView.showMessage(shopGetLocationData.getMessage());
				}
			}

			@Override
			public void onFailure() {
				shopLocationView.showProgressbar(false);
				shopLocationView.showMessage("Unable to connect to Server.");
			}
		});
	}

	@Override
	public void requestChangeLocation(String shop_access_token, Double latitude, Double longitude) {
		shopLocationView.showProgressbar(true);
		shopLocationHelper.requestChangeLocation(shop_access_token, latitude, longitude, new ShopChangeLocationCallback() {
			@Override
			public void onSuccess(ShopChangeLocationData shopChangeLocationData) {
				if (shopChangeLocationData.isSuccess()){
				shopLocationView.showProgressbar(false);
				//shopLocationView.showMessage(shopChangeLocationData.getMessage());
				shopLocationView.onShopLocationChanged();
				}else {
					shopLocationView.showProgressbar(false);
					shopLocationView.showMessage(shopChangeLocationData.getMessage());
				}
			}

			@Override
			public void onFailure(String error) {
				shopLocationView.showProgressbar(false);
				shopLocationView.showMessage("Unable to connect to Server.");
			}
		});
	}
}
