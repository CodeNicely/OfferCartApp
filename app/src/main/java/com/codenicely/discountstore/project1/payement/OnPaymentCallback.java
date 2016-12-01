package com.codenicely.discountstore.project1.payement;

import com.codenicely.discountstore.project1.payement.model.data.PaymentData;

/**
 * Created by meghal on 1/12/16.
 */

public interface OnPaymentCallback {


    void onSuccess(PaymentData paymentData);
    void onFailure();

}
