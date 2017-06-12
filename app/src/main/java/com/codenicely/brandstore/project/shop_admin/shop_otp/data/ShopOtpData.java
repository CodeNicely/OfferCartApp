package com.codenicely.brandstore.project.shop_admin.shop_otp.data;

/**
 * Created by ujjwal on 14/5/17.
 */
public class ShopOtpData {
	private String message;
	private boolean success;
	private String shop_access_token;

	public ShopOtpData(String message, boolean success, String shop_access_token) {
		this.message = message;
		this.success = success;
		this.shop_access_token = shop_access_token;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getAccess_token() {
		return shop_access_token;
	}
}
