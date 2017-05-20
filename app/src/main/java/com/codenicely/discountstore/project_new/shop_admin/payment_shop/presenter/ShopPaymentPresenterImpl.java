package com.codenicely.discountstore.project_new.shop_admin.payment_shop.presenter;


import com.codenicely.discountstore.project_new.shop_admin.payment_shop.PaymentShopCallBack;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.UpdateShopPaymentCallBack;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.model.PaymentShopProvider;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.model.data.ShopPaymentData;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.model.data.UpdateShopPaymentData;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.view.PaymentShopView;

/**
 * Created by aman on 19/5/17.
 */

public class ShopPaymentPresenterImpl implements  ShopPaymentPresenter{

    PaymentShopProvider paymentShopProvider;
    PaymentShopView paymentShopView;


    public ShopPaymentPresenterImpl(PaymentShopProvider paymentShopProvider, PaymentShopView paymentShopView) {
        this.paymentShopProvider = paymentShopProvider;
        this.paymentShopView = paymentShopView;
    }

    @Override
    public void requestShopPaymentHash(double amount, String access_token) {

        paymentShopView.showLoading(true);
        paymentShopProvider.requestShopPaymentHash(amount, access_token, new PaymentShopCallBack() {
            @Override
            public void OnSuccess(ShopPaymentData shopPaymentData) {
                if(shopPaymentData.isSuccess())
                {
                    paymentShopView.showLoading(false);
                    paymentShopView.showMessage(shopPaymentData.getMessage());
                    paymentShopView.proceedToShopPayment(shopPaymentData);
                }
                else
                {
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
    public void updateShopPaymentStatus(String access_token, String transaction_id) {
        paymentShopView.showLoading(true);
        paymentShopProvider.updateShopPaymentStatus(access_token, transaction_id, new UpdateShopPaymentCallBack() {
            @Override
            public void OnSuccess(UpdateShopPaymentData updateShopPaymentData) {
                if(updateShopPaymentData.isSuccess())
                {
                    paymentShopView.showLoading(false);
                    paymentShopView.showMessage(updateShopPaymentData.getMessage());
                }
                else
                {
                    paymentShopView.showLoading(false);
                    paymentShopView.showMessage(updateShopPaymentData.getMessage());


                }


            }

            @Override
            public void OnFailure(String error) {
                paymentShopView.showMessage(error);
                paymentShopView.showLoading(false);

            }
        });



    }
}
