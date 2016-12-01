package com.codenicely.discountstore.project1.payement.model;

import com.codenicely.discountstore.project1.payement.OnPaymentCallback;

/**
 * Created by meghal on 1/12/16.
 */

public interface PaymentProvider {

    void requestPaymentHash(double amount ,String access_token, OnPaymentCallback onPaymentCallback);

}
