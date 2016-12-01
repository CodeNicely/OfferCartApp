package com.codenicely.discountstore.project1.payement.view;

import com.codenicely.discountstore.project1.payement.model.data.PaymentData;

/**
 * Created by meghal on 2/11/16.
 */

public interface PaymentView {


    void showMessage(String message);

    void showLoading(boolean show);

    void proceedToPayment(PaymentData paymentData);
}
