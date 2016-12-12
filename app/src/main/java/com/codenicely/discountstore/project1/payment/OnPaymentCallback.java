package com.codenicely.discountstore.project1.payment;

import com.codenicely.discountstore.project1.payment.model.data.PaymentData;

/**
 * Created by meghal on 1/12/16.
 */

public interface OnPaymentCallback {


    void onSuccess(PaymentData paymentData);
    void onFailure();

}
