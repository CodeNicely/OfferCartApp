package com.codenicely.discountstore.project1.payment.view;

import com.codenicely.discountstore.project1.payment.model.data.PaymentData;

/**
 * Created by meghal on 2/11/16.
 */

public interface PaymentView {


    void showMessage(String message);

    void showLoading(boolean show);

    void proceedToPayment(PaymentData paymentData);
}