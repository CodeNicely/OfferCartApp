package com.codenicely.discountstore.project_new.shop_offer_list.model;


import com.codenicely.discountstore.project_new.shop_offer_list.OnBuyOffer;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOffer_Provider {

    void buyOffer(int offer_id, String access_token, OnBuyOffer onBuyOffer);


}
