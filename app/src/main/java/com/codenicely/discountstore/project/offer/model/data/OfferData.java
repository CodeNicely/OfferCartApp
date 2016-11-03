package com.codenicely.discountstore.project.offer.model.data;

/**
 * Created by meghal on 2/11/16.
 */

public class OfferData {

    private boolean success;
    private String message;

    public OfferData(boolean success, String message) {
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
