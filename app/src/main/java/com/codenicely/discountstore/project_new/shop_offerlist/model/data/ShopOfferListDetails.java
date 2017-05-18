package com.codenicely.discountstore.project_new.shop_offerlist.model.data;

/**
 * Created by aman on 16/5/17.
 */

public class ShopOfferListDetails {

    String offer_title, offer_description, offer_image, offer_price,
              offer_validity_days, offer_validity_date,active;
    int offer_id;

    public ShopOfferListDetails(int offer_id, String offer_title, String offer_description,
                                String offer_image, String offer_price, String offer_validity_days,
                                String offer_validity_date, String active) {
        this.offer_id = offer_id;
        this.offer_title = offer_title;
        this.offer_description = offer_description;
        this.offer_image = offer_image;
        this.offer_price = offer_price;
        this.offer_validity_days = offer_validity_days;
        this.offer_validity_date = offer_validity_date;
        this.active = active;
    }


    public int getOffer_id() {
        return offer_id;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public String getOffer_description() {
        return offer_description;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public String getOffer_validity_days() {
        return offer_validity_days;
    }

    public String getOffer_validity_date() {
        return offer_validity_date;
    }

    public String getActive() {
        return active;
    }
}
