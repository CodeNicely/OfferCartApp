package com.codenicely.discountstore.project_new.shop_offer_list.model;


import com.codenicely.discountstore.project_new.shop_offer_list.OfferScreenDetailsCallback;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsProvider {

    void requestOfferList(String accessToken, OfferScreenDetailsCallback offerScreenDetailsCallback);

}
