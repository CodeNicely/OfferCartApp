package com.codenicely.discountstore.project_new.offer.model.data;

/**
 * Created by meghal on 2/11/16.
 */

public class OfferData {

    private boolean success;
    private String message;
    private int price;


    public OfferData(boolean success, String message, int price) {
        this.success = success;
        this.message = message;
        this.price = price;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getPrice() {
        return price;
    }
}
