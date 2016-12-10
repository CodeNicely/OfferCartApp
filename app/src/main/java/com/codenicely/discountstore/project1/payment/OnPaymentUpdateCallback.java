package com.codenicely.discountstore.project1.payment;

import com.codenicely.discountstore.project1.payment.model.data.UpdatePaymentStatusData;

/**
 * Created by meghal on 10/12/16.
 */

public interface OnPaymentUpdateCallback {


    void onSuccess(UpdatePaymentStatusData updatePaymentStatusData);
    void onFailure();

}
