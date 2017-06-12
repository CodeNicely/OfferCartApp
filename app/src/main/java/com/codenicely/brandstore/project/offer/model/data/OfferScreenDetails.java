package com.codenicely.brandstore.project.offer.model.data;

import java.util.Date;

/**
 * Created by aman on 19/10/16.
 */

public class OfferScreenDetails {

    private String description;
    private int offer_id;
    private String validity;
    private Date expiry_date;
    private String name;
    private String image;
    private int price;


    public OfferScreenDetails(String description, int offer_id, String validity, Date expiry_date,
                              String name, String image, int price) {
        this.description = description;
        this.offer_id = offer_id;
        this.validity = validity;
        this.expiry_date = expiry_date;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public String getDescription() {
        return description;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public String getValidity() {
        return validity;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }
}
