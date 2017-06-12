package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.data;

/**
 * Created by ujjwal on 17/5/17.
 */
public class ShopForgotPasswordData {
	private boolean success;
	private String message;

	public ShopForgotPasswordData(boolean success, String message) {
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
