package com.codenicely.discountstore.project_new.payment_shop.presenter;

/**
 * Created by aman on 19/5/17.
 */

public interface ShopPaymentPresenter {



    void requestShopPaymentHash(double amount , String access_token );
    void updateShopPaymentStatus(String access_token, String transaction_id);


}
