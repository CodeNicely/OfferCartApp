package com.codenicely.discountstore.project_new.shop_offer_list;


import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface OnBuyOffer {


    void onSuccess(OfferData offerData);

    void onFailure();

}
