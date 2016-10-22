package com.example.aman.offercart_v1.offer;

import com.example.aman.offercart_v1.offer.models.data.OfferScreenData;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsCallback
{
    void onSuccess(OfferScreenData offerScreenData);
    void onFailure(String error);

}
