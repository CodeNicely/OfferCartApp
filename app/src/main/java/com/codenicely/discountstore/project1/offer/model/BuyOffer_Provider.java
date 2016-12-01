package com.codenicely.discountstore.project1.offer.model;

import com.codenicely.discountstore.project1.offer.OnBuyOffer;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOffer_Provider {

    void buyOffer(int offer_id, String access_token, OnBuyOffer onBuyOffer);


}
