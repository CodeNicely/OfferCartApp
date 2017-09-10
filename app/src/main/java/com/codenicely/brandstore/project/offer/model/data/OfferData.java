package com.codenicely.brandstore.project.offer.model.data;

import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public class OfferData {

    private List<OfferScreenDetails> offer_list;
    private boolean success;
    private String message;
    private String shop_id;
    private String shop_name;
    private String shop_image;
    private String shop_address;
    private String shop_description;
    private String shop_longitude;
    private String shop_latitude;

    public OfferData(List<OfferScreenDetails> offer_list, boolean success, String message,
                     String shop_id, String shop_name, String shop_image,
                     String shop_address, String shop_description, String shop_longitude,
                     String shop_latitude) {
        this.offer_list = offer_list;
        this.success = success;
        this.message = message;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_image = shop_image;
        this.shop_address = shop_address;
        this.shop_description = shop_description;
        this.shop_longitude = shop_longitude;
        this.shop_latitude = shop_latitude;
    }

    public List<OfferScreenDetails> getOffer_list() {
        return offer_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getShop_longitude() {
        return shop_longitude;
    }

    public String getShop_latitude() {
        return shop_latitude;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getShop_image() {
        return shop_image;
    }

    public String getShop_address() {
        return shop_address;
    }

    public String getShop_description() {
        return shop_description;
    }
}
