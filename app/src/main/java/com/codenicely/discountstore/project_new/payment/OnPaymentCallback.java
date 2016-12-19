package com.codenicely.discountstore.project_new.payment;

import com.codenicely.discountstore.project_new.payment.model.data.PaymentData;

/**
 * Created by meghal on 1/12/16.
 */

public interface OnPaymentCallback {


    void onSuccess(PaymentData paymentData);
    void onFailure();

}
