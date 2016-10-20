package com.example.aman.offercart_v1.offerscreen.models.data;

/**
 * Created by aman on 20/10/16.
 */

public class OfferScreenUpdateData {

    private String offer;
    private boolean success;
    private String message;

    public OfferScreenUpdateData(String offer, boolean success, String message) {
        this.offer = offer;
        this.message= message;
        this.success= success;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOffer() {
        return offer;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
