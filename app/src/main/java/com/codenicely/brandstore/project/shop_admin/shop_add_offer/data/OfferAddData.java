package com.codenicely.brandstore.project.shop_admin.shop_add_offer.data;

/**
 * Created by ujjwal on 15/5/17.
 */
public class OfferAddData {
	boolean success;
	String message;

	public OfferAddData(boolean success, String message) {
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
