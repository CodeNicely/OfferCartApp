package com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data;

import java.util.List;

/**
 * Created by aman on 16/5/17.
 */

public class ShopOfferListData {
    boolean success;
    String message,subscription_description,subscription_button_description,shop_name;
    List<ShopOfferListDetails> shop_offer_list;

	public ShopOfferListData(boolean success, String message, String subscription_description,
							 String subscription_button_description,
							 String shop_name, List<ShopOfferListDetails> shop_offer_list) {
		this.success = success;
		this.message = message;
		this.subscription_description = subscription_description;
		this.subscription_button_description = subscription_button_description;
		this.shop_name = shop_name;
		this.shop_offer_list = shop_offer_list;
	}

	public String getSubscription_button_description() {
		return subscription_button_description;
	}

	public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getSubscription_description() {
        return subscription_description;
    }

    public String getShop_name() {
        return shop_name;
    }

    public List<ShopOfferListDetails> getShop_offer_list() {
        return shop_offer_list;
    }
}
