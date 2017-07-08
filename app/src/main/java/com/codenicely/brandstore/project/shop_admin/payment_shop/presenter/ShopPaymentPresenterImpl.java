package com.codenicely.brandstore.project.shop_admin.payment_shop.presenter;


import android.util.Log;

import com.codenicely.brandstore.project.shop_admin.payment_shop.PaymentShopCallBack;
import com.codenicely.brandstore.project.shop_admin.payment_shop.UpdateShopPaymentCallBack;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.PaymentShopProvider;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.ShopPaymentData;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.UpdateShopPaymentData;
import com.codenicely.brandstore.project.shop_admin.payment_shop.view.PaymentShopView;

/**
 * Created by aman on 19/5/17.
 */

public class ShopPaymentPresenterImpl implements ShopPaymentPresenter {

    PaymentShopProvider paymentShopProvider;
    PaymentShopView paymentShopView;


    public ShopPaymentPresenterImpl(PaymentShopProvider paymentShopProvider, PaymentShopView paymentShopView) {
        this.paymentShopProvider = paymentShopProvider;
        this.paymentShopView = paymentShopView;
    }

    @Override
    public void requestShopPaymentHash(final int payment_type, int id, final int days, String access_token) {

        paymentShopView.showLoading(true);
        paymentShopProvider.requestShopPaymentHash(id, access_token, new PaymentShopCallBack() {
            @Override
            public void OnSuccess(ShopPaymentData shopPaymentData) {
                if (shopPaymentData.isSuccess()) {
                    paymentShopView.showLoading(false);
//                    paymentShopView.showMessage(shopPaymentData.getMessage());
                    paymentShopView.proceedToShopPayment(shopPaymentData,days,payment_type);
                    Log.d("Hash", shopPaymentData.getChecksum_hash());
                } else {
                    Log.d("False", "failure");
                    paymentShopView.showLoading(false);
                    paymentShopView.showMessage(shopPaymentData.getMessage());
                }


            }

            @Override
            public void OnFailure(String error) {
                paymentShopView.showMessage(error);
                paymentShopView.showLoading(false);


            }
        });

    }

    @Override
    public void updateShopPaymentStatus(String access_token, String transaction_id, Boolean success) {
        paymentShopView.showLoading(true);
        paymentShopProvider.updateShopPaymentStatus(access_token, transaction_id, success, new UpdateShopPaymentCallBack() {
            @Override
            public void OnSuccess(UpdateShopPaymentData updateShopPaymentData) {
                if (updateShopPaymentData.isSuccess()) {
                    paymentShopView.showLoading(false);
                    paymentShopView.showMessage(updateShopPaymentData.getMessage());
                    // paymentShopView.showMessage(updateShopPaymentData.getMessage());
                    Log.d("Aman_confirm", "aman confirm");
                } else {
                    paymentShopView.showLoading(false);
                    paymentShopView.showMessage(updateShopPaymentData.getMessage());
                    Log.d("False2", updateShopPaymentData.getMessage());
                }
            }

            @Override
            public void OnFailure(String error) {
                paymentShopView.showMessage(error);
                paymentShopView.showLoading(false);
                Log.d("Aman", "aman");

            }
        });
    }
}
