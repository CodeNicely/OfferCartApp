package com.codenicely.discountstore.project_new.shop_admin.shop_offerlist;

import com.codenicely.discountstore.project_new.shop_admin.shop_offerlist.model.data.ShopOfferListData;

/**
 * Created by aman on 16/5/17.
 */

public interface ShopOfferCallBack {
    void onSuccess(ShopOfferListData shopOfferListData);
    void onFailure(String error);




}
