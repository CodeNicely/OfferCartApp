package com.codenicely.brandstore.project.shop_admin.shop_profile_show.data;

/**
 * Created by ujjwal on 16/5/17.
 */
public class ShowShopProfileData {
	private boolean success;
	private String message;
	private String name;
	private String mobile;
	private String description;
	private String address;
	private String category;
	private String city;
	private String image;

	public ShowShopProfileData(boolean success, String message, String name, String mobile,
							   String description,
							   String address, String category, String city, String image) {
		this.success = success;
		this.message = message;
		this.name = name;
		this.mobile = mobile;
		this.description = description;
		this.address = address;
		this.category = category;
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
		return name;
	}

	public String getMobile_number() {
		return mobile;
	}

	public String getShop_description() {
		return description;
	}

	public String getShop_address() {
		return address;
	}

	public String getShop_category() {
		return category;
	}

	public String getCity() {
		return city;
	}

	public String getImage() {
		return image;
	}
}
