package com.codenicely.brandstore.project.offer;

import com.codenicely.brandstore.project.offer.model.data.OfferData;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsCallback {
    void onSuccess(OfferData offerScreenData);

    void onFailure(String error);

}
