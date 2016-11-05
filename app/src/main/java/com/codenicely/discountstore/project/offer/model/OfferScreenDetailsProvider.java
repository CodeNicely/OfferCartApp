package com.codenicely.discountstore.project.offer.model;

import com.codenicely.discountstore.project.offer.OfferScreenDetailsCallback;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsProvider {

    void requestOfferList(String offerToken, int shop_id, OfferScreenDetailsCallback offerScreenDetailsCallback);

}
