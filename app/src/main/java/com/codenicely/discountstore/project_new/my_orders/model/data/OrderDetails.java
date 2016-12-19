package com.codenicely.discountstore.project_new.my_orders.model.data;

/**
 * Created by iket on 3/11/16.
 */
public class OrderDetails {
    private String offer_id,offer_name,offer_code,shop_address,shop_name,offer_validity,offer_price;

    public OrderDetails(String offer_id, String offer_name, String offer_code, String shop_address, String shop_name, String offer_validity, String offer_price) {
        this.offer_id = offer_id;
        this.offer_name = offer_name;
        this.offer_code = offer_code;
        this.shop_address = shop_address;
        this.shop_name = shop_name;
        this.offer_validity = offer_validity;
        this.offer_price = offer_price;
    }


    public String getOffer_id() {
        return offer_id;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public String getOffer_code() {
        return offer_code;
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

    public String getOffer_price() {
        return offer_price;
    }
}
