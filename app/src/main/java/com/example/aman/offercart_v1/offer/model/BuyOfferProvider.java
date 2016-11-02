package com.example.aman.offercart_v1.offer.model;

import com.example.aman.offercart_v1.offer.OnBuyOffer;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOfferProvider {

    void buyOffer(int offer_id, String access_token, OnBuyOffer onBuyOffer);


}
