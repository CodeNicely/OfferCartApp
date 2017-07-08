package com.codenicely.brandstore.project.shop_admin.payment_shop.view;

import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.ShopPaymentData;

/**
 * Created by aman on 19/5/17.
 */

public interface PaymentShopView {
    void showMessage(String message);

    void showLoading(boolean show);

    void proceedToShopPayment(ShopPaymentData shopPaymentData,int days,int payment_type);
}
