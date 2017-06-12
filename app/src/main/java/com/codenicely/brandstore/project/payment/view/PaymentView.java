package com.codenicely.brandstore.project.payment.view;

import com.codenicely.brandstore.project.payment.model.data.PaymentData;

/**
 * Created by meghal on 2/11/16.
 */

public interface PaymentView {


    void showMessage(String message);

    void showLoading(boolean show);

    void proceedToPayment(PaymentData paymentData);
}
