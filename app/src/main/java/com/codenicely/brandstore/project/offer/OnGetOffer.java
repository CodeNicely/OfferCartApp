package com.codenicely.brandstore.project.offer;


import com.codenicely.brandstore.project.offer.model.data.OfferGetData;

/**
 * Created by meghal on 2/11/16.
 */

public interface OnGetOffer {


    void onSuccess(OfferGetData offerData);

    void onFailure();

}
