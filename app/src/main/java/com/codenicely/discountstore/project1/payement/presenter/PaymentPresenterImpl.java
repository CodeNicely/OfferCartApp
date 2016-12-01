package com.codenicely.discountstore.project1.payement.presenter;

import com.codenicely.discountstore.project1.payement.OnPaymentCallback;
import com.codenicely.discountstore.project1.payement.model.PaymentProvider;
import com.codenicely.discountstore.project1.payement.model.data.PaymentData;
import com.codenicely.discountstore.project1.payement.view.PaymentView;

/**
 * Created by meghal on 1/12/16.
 */

public class PaymentPresenterImpl implements PaymentPresenter {

    private PaymentView paymentView;
    private PaymentProvider paymentProvider;

    public PaymentPresenterImpl(PaymentView paymentView, PaymentProvider paymentProvider) {
        this.paymentView = paymentView;
        this.paymentProvider = paymentProvider;
    }

    @Override
    public void requestPaymentHash(double amount, String access_token) {

        paymentView.showLoading(true);
        paymentProvider.requestPaymentHash(amount, access_token, new OnPaymentCallback() {
            @Override
            public void onSuccess(PaymentData paymentData) {

                paymentView.showLoading(false);
                paymentView.showMessage(paymentData.getMessage());

                if (paymentData.isSuccess()) {
                    paymentView.proceedToPayment(paymentData);

                }

            }

            @Override
            public void onFailure() {

                paymentView.showLoading(false);
                paymentView.showMessage("Unable To Connect Our Servers ! Please Check Your Internet Connection");

            }
        });


    }
}
