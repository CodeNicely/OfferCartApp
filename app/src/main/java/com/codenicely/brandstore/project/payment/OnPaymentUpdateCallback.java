package com.codenicely.brandstore.project.payment;

import com.codenicely.brandstore.project.payment.model.data.UpdatePaymentStatusData;

/**
 * Created by meghal on 10/12/16.
 */

public interface OnPaymentUpdateCallback {


    void onSuccess(UpdatePaymentStatusData updatePaymentStatusData);
    void onFailure();

}
