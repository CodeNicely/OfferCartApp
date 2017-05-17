package com.codenicely.discountstore.project_new.shop_profile_show.data;

/**
 * Created by ujjwal on 16/5/17.
 */
public class ShowShopProfileData {
	private boolean success;
	private String message;
	private String shop_name;
	private String mobile_number;
	private String shop_description;
	private String shop_address;
	private String shop_category;
	private String city;
	private String image;

	public ShowShopProfileData(boolean success, String message, String shop_name, String mobile_number,
							   String shop_description, String shop_address, String shop_category,
							   String city, String image) {
		this.success = success;
		this.message = message;
		this.shop_name = shop_name;
		this.mobile_number = mobile_number;
		this.shop_description = shop_description;
		this.shop_address = shop_address;
		this.shop_category = shop_category;
		this.city = city;
		this.image = image;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getShop_name() {
		return shop_name;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public String getShop_description() {
		return shop_description;
	}

	public String getShop_address() {
		return shop_address;
	}

	public String getShop_category() {
		return shop_category;
	}

	public String getCity() {
		return city;
	}

	public String getImage() {
		return image;
	}
}
