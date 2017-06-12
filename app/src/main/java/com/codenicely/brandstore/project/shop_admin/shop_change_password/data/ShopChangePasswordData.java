package com.codenicely.brandstore.project.shop_admin.shop_change_password.data;

/**
 * Created by ujjwal on 17/5/17.
 */
public class ShopChangePasswordData {
	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public ShopChangePasswordData(boolean success, String message) {

		this.success = success;
		this.message = message;
	}
}
