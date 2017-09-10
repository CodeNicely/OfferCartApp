package com.codenicely.brandstore.project.shop_admin.shop_change_location.data;

/**
 * Created by ujjwal on 9/9/17.
 */

public class ShopGetLocationData {
	private boolean success;
	private String message;
	private String latitude;
	private String longitude;

	public ShopGetLocationData(boolean success, String message, String latitude, String longitude) {
		this.success = success;
		this.message = message;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
}
