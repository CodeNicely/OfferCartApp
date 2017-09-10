package com.codenicely.brandstore.project.shop_admin.shop_change_location.data;

/**
 * Created by ujjwal on 9/9/17.
 */

public class ShopChangeLocationData {
	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public ShopChangeLocationData(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
}
