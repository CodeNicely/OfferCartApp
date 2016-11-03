package com.codenicely.discountstore.project.offer;

import com.codenicely.discountstore.project.offer.model.data.OfferScreenList;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsCallback {
    void onSuccess(OfferScreenList offerScreenData);

    void onFailure(String error);

}
