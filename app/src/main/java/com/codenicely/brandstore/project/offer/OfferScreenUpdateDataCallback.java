package com.codenicely.brandstore.project.offer;

import com.codenicely.brandstore.project.offer.model.data.OfferScreenUpdateData;

/**
 * Created by aman on 20/10/16.
 */

public interface OfferScreenUpdateDataCallback {

    void onSuccess(OfferScreenUpdateData offerScreenUpdateData);

    void onFailure(String error);
}
