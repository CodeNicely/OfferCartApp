package com.codenicely.discountstore.project_new.payment.model.data;

/**
 * Created by meghal on 10/12/16.
 */

public class UpdatePaymentStatusData {

    boolean success;
    String message;


    public UpdatePaymentStatusData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
