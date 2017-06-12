package com.codenicely.brandstore.project.shop_admin.payment_shop;

import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.ShopPaymentData;

/**
 * Created by aman on 19/5/17.
 */

public interface PaymentShopCallBack {
    void OnSuccess(ShopPaymentData shopPaymentData);
    void OnFailure(String error);
}
