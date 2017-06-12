package com.codenicely.brandstore.project.shop_admin.shop_offerlist.presenter;

/**
 * Created by aman on 16/5/17.
 */

public interface ShopOfferListPresenter {

    void requestShopOffer(String access_token);
    void delete(String access_token,int offer_id);


}




