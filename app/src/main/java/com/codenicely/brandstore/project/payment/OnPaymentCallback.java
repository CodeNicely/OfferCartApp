package com.codenicely.brandstore.project.payment;

import com.codenicely.brandstore.project.payment.model.data.PaymentData;

/**
 * Created by meghal on 1/12/16.
 */

public interface OnPaymentCallback {


    void onSuccess(PaymentData paymentData);
    void onFailure();

}
