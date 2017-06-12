package com.codenicely.brandstore.project.offer;


import com.codenicely.brandstore.project.offer.model.data.OfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface OnGetOffer {


    void onSuccess(OfferData offerData);

    void onFailure();

}
