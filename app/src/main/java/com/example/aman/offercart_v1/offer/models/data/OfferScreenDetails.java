package com.example.aman.offercart_v1.offer.models.data;

/**
 * Created by aman on 19/10/16.
 */

public class OfferScreenDetails {

    private String offer_des;
    private int data_type;
    private int offer_id;
    private String offer_code;
    private String offer_validdate;
    private String offer_name;
    private String offer_image;

    public OfferScreenDetails(String offer_des, int data_type, int offer_data, String offer_code,
                              String offer_validdate, String offer_name, String offer_image)
    {
        this.offer_des = offer_des;
        this.data_type = data_type;
        this.offer_id = offer_data;
        this.offer_code = offer_code;
        this.offer_validdate = offer_validdate;
        this.offer_name = offer_name;
        this.offer_image = offer_image;
    }

    public String getOffer_des() {
        return offer_des;
    }

    public int getData_type() {
        return data_type;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public String getOffer_code() {
        return offer_code;
    }

    public String getOffer_validdate() {
        return offer_validdate;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public String getOffer_image() {
        return offer_image;
    }
}
