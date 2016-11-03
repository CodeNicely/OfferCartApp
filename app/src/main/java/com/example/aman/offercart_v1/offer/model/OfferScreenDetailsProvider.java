package com.example.aman.offercart_v1.offer.model;

import com.example.aman.offercart_v1.offer.OfferScreenDetailsCallback;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsProvider {

    void requestOfferList(String offerToken, String shop_id, OfferScreenDetailsCallback offerScreenDetailsCallback);

}
