package com.codenicely.brandstore.project.shop_admin.shop_login.data;

/**
 * Created by ujjwal on 15/5/17.
 */
public class ShopLoginData {
	private boolean success;
	private String message;
	private String shop_access_token;

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getAccess_token() {
		return shop_access_token;
	}

	public ShopLoginData(boolean success, String message, String shop_access_token) {
		this.success = success;
		this.message = message;
		this.shop_access_token = shop_access_token;
	}
}
