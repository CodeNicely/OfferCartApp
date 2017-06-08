package com.codenicely.discountstore.project_new.offer;


import com.codenicely.discountstore.project_new.offer.model.data.OfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface OnGetOffer {


    void onSuccess(OfferData offerData);

    void onFailure();

}
