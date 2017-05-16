package com.codenicely.discountstore.project_new.shop_login.data;

/**
 * Created by ujjwal on 15/5/17.
 */
public class ShopLoginData {
	private boolean success;
	private String message;
	private String access_token;

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getAccess_token() {
		return access_token;
	}

	public ShopLoginData(boolean success, String message, String access_token) {
		this.success = success;
		this.message = message;
		this.access_token = access_token;
	}
}
