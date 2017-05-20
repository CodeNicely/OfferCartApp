package com.codenicely.discountstore.project_new.shop_admin.payment_shop.model;


import com.codenicely.discountstore.project_new.shop_admin.payment_shop.PaymentShopCallBack;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.UpdateShopPaymentCallBack;

/**
 * Created by aman on 19/5/17.
 */

public interface PaymentShopProvider {



    void requestShopPaymentHash(int id, String access_token, PaymentShopCallBack paymentShopCallBack);
    void updateShopPaymentStatus(String access_token, String transaction_id,Boolean success, UpdateShopPaymentCallBack updateShopPaymentCallBack);
}
