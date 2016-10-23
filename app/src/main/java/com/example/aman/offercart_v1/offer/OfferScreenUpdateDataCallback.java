package com.example.aman.offercart_v1.offer;

import com.example.aman.offercart_v1.offer.models.data.OfferScreenUpdateData;

/**
 * Created by aman on 20/10/16.
 */

public interface OfferScreenUpdateDataCallback {

    void onSuccess(OfferScreenUpdateData offerScreenUpdateData);
    void onFailure(String error);
}
