package com.codenicely.discountstore.project_new.payment.model;

import com.codenicely.discountstore.project_new.payment.OnPaymentCallback;
import com.codenicely.discountstore.project_new.payment.OnPaymentUpdateCallback;

/**
 * Created by meghal on 1/12/16.
 */

public interface PaymentProvider {

    void requestPaymentHash(double amount ,String access_token, OnPaymentCallback onPaymentCallback);
    void updatePaymentStatus(String access_token, String transaction_id, OnPaymentUpdateCallback onPaymentUpdateCallback);
}
