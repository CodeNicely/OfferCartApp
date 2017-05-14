package com.codenicely.discountstore.project_new.shop_otp.data;

/**
 * Created by ujjwal on 14/5/17.
 */
public class ShopOtpData {
	private String message;
	private boolean success;
	private String access_token;

	public ShopOtpData(String message, boolean success, String access_token) {
		this.message = message;
		this.success = success;
		this.access_token = access_token;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getAccess_token() {
		return access_token;
	}
}
