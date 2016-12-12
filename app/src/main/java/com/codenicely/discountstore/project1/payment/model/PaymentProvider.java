package com.codenicely.discountstore.project1.payment.model;

import com.codenicely.discountstore.project1.payment.OnPaymentCallback;
import com.codenicely.discountstore.project1.payment.OnPaymentUpdateCallback;

/**
 * Created by meghal on 1/12/16.
 */

public interface PaymentProvider {

    void requestPaymentHash(double amount ,String access_token, OnPaymentCallback onPaymentCallback);
    void updatePaymentStatus(String access_token, String transaction_id, OnPaymentUpdateCallback onPaymentUpdateCallback);
}
