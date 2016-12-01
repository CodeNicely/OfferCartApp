package com.codenicely.discountstore.project1.offer;

import com.codenicely.discountstore.project1.offer.model.data.OfferScreenUpdateData;

/**
 * Created by aman on 20/10/16.
 */

public interface OfferScreenUpdateDataCallback {

    void onSuccess(OfferScreenUpdateData offerScreenUpdateData);

    void onFailure(String error);
}
