package com.codenicely.discountstore.project_new.shop_offerlist.presenter;

import com.codenicely.discountstore.project_new.shop_offerlist.ShopOfferCallBack;

/**
 * Created by aman on 16/5/17.
 */

public interface ShopOfferListPresenter {

    void requestShopOffer(String access_token);
    void delete(String access_token,int offer_id);


}




