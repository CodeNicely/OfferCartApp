package com.codenicely.brandstore.project.offer;

import com.codenicely.brandstore.project.offer.model.data.OfferScreenList;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsCallback {
    void onSuccess(OfferScreenList offerScreenData);

    void onFailure(String error);

}
