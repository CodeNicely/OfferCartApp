package com.codenicely.brandstore.project.offer.model;

import com.codenicely.brandstore.project.offer.OfferScreenDetailsCallback;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsProvider {

    void requestOfferList(String offerToken, int shop_id, OfferScreenDetailsCallback offerScreenDetailsCallback);

}
