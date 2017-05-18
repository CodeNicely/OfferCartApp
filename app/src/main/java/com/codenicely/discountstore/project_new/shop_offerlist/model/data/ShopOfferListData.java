package com.codenicely.discountstore.project_new.shop_offerlist.model.data;

import java.util.List;

/**
 * Created by aman on 16/5/17.
 */

public class ShopOfferListData {
    boolean success;
    String message,subscription_validity,shop_name;
    List<ShopOfferListDetails> shop_offer_list;


    public ShopOfferListData(boolean success, String message, String subscription_validity,
                             String shop_name, List<ShopOfferListDetails> shop_offer_list) {
        this.success = success;
        this.message = message;
        this.subscription_validity = subscription_validity;
        this.shop_name = shop_name;
        this.shop_offer_list = shop_offer_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getSubscription_validity() {
        return subscription_validity;
    }

    public String getShop_name() {
        return shop_name;
    }

    public List<ShopOfferListDetails> getShop_offer_list() {
        return shop_offer_list;
    }
}
