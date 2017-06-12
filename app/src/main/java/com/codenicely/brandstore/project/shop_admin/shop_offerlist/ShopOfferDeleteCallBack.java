package com.codenicely.brandstore.project.shop_admin.shop_offerlist;

import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data.ShopOfferDeleteData;

/**
 * Created by aman on 18/5/17.
 */

public interface ShopOfferDeleteCallBack {
    void onSuccess(ShopOfferDeleteData shopOfferDeleteData);
    void onFailure(String error);


}
