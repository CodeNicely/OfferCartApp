package com.example.aman.offercart_v1.offer;


import com.example.aman.offercart_v1.offer.model.data.OfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface OnBuyOffer {


    void onSuccess(OfferData offerData);

    void onFailure();

}
