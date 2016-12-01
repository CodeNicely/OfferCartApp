package com.codenicely.discountstore.project1.offer;


import com.codenicely.discountstore.project1.offer.model.data.OfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface OnBuyOffer {


    void onSuccess(OfferData offerData);

    void onFailure();

}
