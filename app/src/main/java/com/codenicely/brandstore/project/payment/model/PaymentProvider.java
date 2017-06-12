package com.codenicely.brandstore.project.payment.model;

import com.codenicely.brandstore.project.payment.OnPaymentCallback;
import com.codenicely.brandstore.project.payment.OnPaymentUpdateCallback;

/**
 * Created by meghal on 1/12/16.
 */

public interface PaymentProvider {

    void requestPaymentHash(double amount ,String access_token, OnPaymentCallback onPaymentCallback);
    void updatePaymentStatus(String access_token, String transaction_id, OnPaymentUpdateCallback onPaymentUpdateCallback);
}
