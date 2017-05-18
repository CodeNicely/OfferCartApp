package com.codenicely.discountstore.project_new.shop_offerlist;

import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferDeleteData;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListData;

/**
 * Created by aman on 18/5/17.
 */

public interface ShopOfferDeleteCallBack {
    void onSuccess(ShopOfferDeleteData shopOfferDeleteData);
    void onFailure(String error);


}
