package com.codenicely.brandstore.project.offer.model.data;

/**
 * Created by aman on 20/10/16.
 */

public class OfferScreenUpdateData {

    private int offer_id;
    private String offer_code;
    private String offer_name;

    private boolean success;
    private String message;

    public OfferScreenUpdateData(int offer_id, String offer_code, String offer_name, boolean success, String message) {
        this.offer_id = offer_id;
        this.offer_code = offer_code;
        this.offer_name = offer_name;
        this.success = success;
        this.message = message;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public String getOffer_code() {
        return offer_code;
    }

    public void setOffer_code(String offer_code) {
        this.offer_code = offer_code;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
