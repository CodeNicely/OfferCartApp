package com.codenicely.brandstore.project.payment.presenter;

/**
 * Created by meghal on 1/12/16.
 */

public interface PaymentPresenter {

    void requestPaymentHash(double amount,String access_token);
    void updatePaymentStatus(String access_token,String transaction_id);

}
