package com.codenicely.discountstore.project_new.shop_offerlist.model.data;

/**
 * Created by aman on 16/5/17.
 */

public class ShopOfferListDetails {

    String offer_title, offer_description, offer_image,
               expiry_date;
    int offer_id,validity;
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
        return validity;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public ShopOfferListDetails(String offer_title, String offer_description, String offer_image, String expiry_date, int offer_id, int validity, boolean active) {
        this.offer_title = offer_title;
        this.offer_description = offer_description;
        this.offer_image = offer_image;
        this.expiry_date = expiry_date;
        this.offer_id = offer_id;
        this.validity = validity;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
