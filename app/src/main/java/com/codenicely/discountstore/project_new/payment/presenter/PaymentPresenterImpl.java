package com.codenicely.discountstore.project_new.payment.presenter;

import com.codenicely.discountstore.project_new.payment.OnPaymentCallback;
import com.codenicely.discountstore.project_new.payment.OnPaymentUpdateCallback;
import com.codenicely.discountstore.project_new.payment.model.PaymentProvider;
import com.codenicely.discountstore.project_new.payment.model.data.PaymentData;
import com.codenicely.discountstore.project_new.payment.model.data.UpdatePaymentStatusData;
import com.codenicely.discountstore.project_new.payment.view.PaymentView;

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

    @Override
    public void updatePaymentStatus(String access_token, String transaction_id) {

        paymentView.showLoading(true);
        paymentProvider.updatePaymentStatus(access_token, transaction_id, new OnPaymentUpdateCallback() {
            @Override
            public void onSuccess(UpdatePaymentStatusData updatePaymentStatusData) {
                paymentView.showLoading(false);
                if (updatePaymentStatusData.isSuccess()) {
                    paymentView.showMessage(updatePaymentStatusData.getMessage());

                } else {
                    paymentView.showMessage(updatePaymentStatusData.getMessage());
                }
            }

            @Override
            public void onFailure() {
                paymentView.showLoading(false);
                paymentView.showMessage("Unable to connect to servers . Please Try Again");
            }
        });
    }
}
