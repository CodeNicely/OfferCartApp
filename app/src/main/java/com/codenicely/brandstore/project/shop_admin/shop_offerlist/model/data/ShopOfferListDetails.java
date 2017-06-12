package com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data;

/**
 * Created by aman on 16/5/17.
 */

public class ShopOfferListDetails {

    String offer_title, offer_description, offer_image,
               offer_expiry_date;
    int offer_id,offer_validity_days;

    boolean active;

    public int getOffer_id() {
        return offer_id;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public String getOffer_description() {
        return offer_description;
    }

    public int getValidity() {
        return offer_validity_days;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public String getExpiry_date() {
        return offer_expiry_date;
    }

	public ShopOfferListDetails(String offer_title, String offer_description, String offer_image,
			String offer_expiry_date, int offer_id, int validity, boolean active) {
		this.offer_title = offer_title;
		this.offer_description = offer_description;
		this.offer_image = offer_image;
		this.offer_expiry_date = offer_expiry_date;
		this.offer_id = offer_id;
		this.offer_validity_days = validity;
		this.active = active;
	}

	public boolean isActive() {
        return active;
    }
}
