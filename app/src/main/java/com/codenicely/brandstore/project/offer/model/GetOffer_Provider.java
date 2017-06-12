package com.codenicely.brandstore.project.offer.model;

import com.codenicely.brandstore.project.offer.OnGetOffer;

/**
 * Created by meghal on 2/11/16.
 */

public interface GetOffer_Provider {

    void getOffer(int offer_id, String access_token, OnGetOffer onGetOffer);
}
