package com.codenicely.discountstore.project_new.shop_admin.payment_shop.view;

import com.codenicely.discountstore.project_new.shop_admin.payment_shop.model.data.ShopPaymentData;

/**
 * Created by aman on 19/5/17.
 */

public interface PaymentShopView {
    void showMessage(String message);

    void showLoading(boolean show);

    void proceedToShopPayment(ShopPaymentData shopPaymentData);
}
