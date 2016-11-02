package com.example.aman.offercart_v1.offer.model.data;

/**
 * Created by meghal on 2/11/16.
 */

public class BuyOfferData {

    private boolean success;
    private String message;

    public BuyOfferData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
