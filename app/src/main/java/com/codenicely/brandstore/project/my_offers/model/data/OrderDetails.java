package com.codenicely.brandstore.project.my_offers.model.data;

/**
 * Created by iket on 3/11/16.
 */
public class OrderDetails {
    private String offer_id,offer_image,offer_name,offer_description,shop_address,shop_name,offer_validity;

	public OrderDetails(String offer_id, String offer_image, String offer_name, String offer_description,
						String shop_address, String shop_name, String offer_validity) {
		this.offer_id = offer_id;
		this.offer_image = offer_image;
		this.offer_name = offer_name;
		this.offer_description = offer_description;
		this.shop_address = shop_address;
		this.shop_name = shop_name;
		this.offer_validity = offer_validity;
	}

	public String getOffer_id() {
        return offer_id;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public String getOffer_name() {
        return offer_name;
    }


    public String getOffer_description() {
        return offer_description;
    }

    public String getShop_address() {
        return shop_address;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getOffer_validity() {
        return offer_validity;
    }

}
