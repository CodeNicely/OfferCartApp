package com.codenicely.discountstore.project_new.offer_add.presenter;

import android.net.Uri;

import java.util.Date;

/**
 * Created by ujjwal on 12/5/17.
 */
public interface OfferAddPresenter {
	void openCamera();

	/**
	 * This function s called from view if user chooses to select images already present in gallery
	 */
	void openGallery();


	void addOffer(String keyAccessTokenShop, String name, String description, int price,
				  int date, int month, int year, Uri imageUri);

}
