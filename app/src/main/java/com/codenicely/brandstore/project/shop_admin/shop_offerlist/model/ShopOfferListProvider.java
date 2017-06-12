package com.codenicely.brandstore.project.shop_admin.shop_offerlist.model;

import com.codenicely.brandstore.project.shop_admin.shop_offerlist.ShopOfferCallBack;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.ShopOfferDeleteCallBack;

/**
 * Created by aman on 16/5/17.
 */

public interface ShopOfferListProvider {

    void requestShopOffer(String access_token, ShopOfferCallBack shopOfferCallBack);

    void delete(String access_token, int offer_id, ShopOfferDeleteCallBack shopOfferDeleteCallBack);

}
