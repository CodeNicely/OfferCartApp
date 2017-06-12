package com.codenicely.brandstore.project.shop_admin.shop_add_offer.presenter;

import android.net.Uri;

/**
 * Created by ujjwal on 12/5/17.
 */
public interface OfferAddPresenter {
	void openCamera();

	/**
	 * This function s called from view if user chooses to select images already present in gallery
	 */
	void openGallery();


	void addOffer(String keyAccessTokenShop, String name, String description,
				  int date, int month, int year, Uri imageUri);

}
