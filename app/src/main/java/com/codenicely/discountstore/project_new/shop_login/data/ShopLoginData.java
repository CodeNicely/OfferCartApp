package com.codenicely.discountstore.project_new.shop_login.data;

/**
 * Created by ujjwal on 15/5/17.
 */
public class ShopLoginData {
	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public ShopLoginData(boolean success, String message) {

		this.success = success;
		this.message = message;
	}
}
