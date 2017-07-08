package com.codenicely.brandstore.project.shop_admin.payment_shop.presenter;

/**
 * Created by aman on 19/5/17.
 */

public interface ShopPaymentPresenter {



    void requestShopPaymentHash(int payment_type,int id ,int days, String access_token );
    void updateShopPaymentStatus(String access_token, String transaction_id,Boolean success);


}
