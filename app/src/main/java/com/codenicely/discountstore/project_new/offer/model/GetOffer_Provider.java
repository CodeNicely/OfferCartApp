package com.codenicely.discountstore.project_new.offer.model;

import com.codenicely.discountstore.project_new.offer.OnGetOffer;

/**
 * Created by meghal on 2/11/16.
 */

public interface GetOffer_Provider {

    void getOffer(int offer_id, String access_token, OnGetOffer onGetOffer);
}
