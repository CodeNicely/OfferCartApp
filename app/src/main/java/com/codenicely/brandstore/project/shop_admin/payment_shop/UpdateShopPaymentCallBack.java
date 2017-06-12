package com.codenicely.brandstore.project.shop_admin.payment_shop;

import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.UpdateShopPaymentData;

/**
 * Created by aman on 19/5/17.
 */

public interface UpdateShopPaymentCallBack {



    void OnSuccess(UpdateShopPaymentData updateShopPaymentData);
    void OnFailure(String error);
}
