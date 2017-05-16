package com.codenicely.discountstore.project_new.shop_offer_list.model.data;

import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public class OfferScreenList {
    private List<com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferScreenDetails> offer_list;
    private boolean success;
    private String message;


    public OfferScreenList(List<com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferScreenDetails> offer_list,
        boolean success, String message, String shop_id, String shop_name, String shop_image,
                           String shop_address, String shop_description) {
        this.offer_list = offer_list;
        this.success = success;
        this.message = message;
       }

    public List<com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferScreenDetails> getOffer_list() {
        return offer_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

 }
