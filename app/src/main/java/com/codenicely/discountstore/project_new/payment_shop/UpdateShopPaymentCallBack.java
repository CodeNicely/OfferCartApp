package com.codenicely.discountstore.project_new.payment_shop;

import com.codenicely.discountstore.project_new.payment_shop.model.data.ShopPaymentData;
import com.codenicely.discountstore.project_new.payment_shop.model.data.UpdateShopPaymentData;

/**
 * Created by aman on 19/5/17.
 */

public interface UpdateShopPaymentCallBack {



    void OnSuccess(UpdateShopPaymentData updateShopPaymentData);
    void OnFailure(String error);
}
