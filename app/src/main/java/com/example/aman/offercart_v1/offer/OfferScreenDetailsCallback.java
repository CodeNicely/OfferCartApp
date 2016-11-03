package com.example.aman.offercart_v1.offer;

import com.example.aman.offercart_v1.offer.model.data.OfferScreenList;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsCallback {
    void onSuccess(OfferScreenList offerScreenData);

    void onFailure(String error);

}
