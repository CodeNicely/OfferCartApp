package com.example.aman.offercart_v1.offerscreen;

import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenUpdateData;

/**
 * Created by aman on 20/10/16.
 */

public interface OfferScreenUpdateDataCallback {

    void onSuccess(OfferScreenUpdateData offerScreenUpdateData);
    void onFailure(String error);
}
