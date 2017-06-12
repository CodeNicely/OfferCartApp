package com.codenicely.brandstore.project.shop_admin.shop_profile_edit.presenter;

import android.net.Uri;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface EditShopProfilePresenter {
	void openCamera();

	/**
	 * This function s called from view if user chooses to select images already present in gallery
	 */
	void openGallery();

	void editShopProfile(String access_token,String name, String description, String address,
					  String category, String city,Double latitude,Double longitude, Uri imageUri);

	void requestPreRegistrationDetails();

	void requestCityList(int state_id);
}
