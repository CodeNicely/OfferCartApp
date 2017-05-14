package com.codenicely.discountstore.project_new.offer_add.data;

/**
 * Created by ujjwal on 15/5/17.
 */
public class OfferRegisterData {
	boolean success;
	String message;

	public OfferRegisterData(boolean success, String message) {
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
