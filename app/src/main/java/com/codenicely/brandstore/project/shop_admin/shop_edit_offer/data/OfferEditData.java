package com.codenicely.brandstore.project.shop_admin.shop_edit_offer.data;

/**
 * Created by ujjwal on 15/5/17.
 */
public class OfferEditData {
	boolean success;
	String message;

	public OfferEditData(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
