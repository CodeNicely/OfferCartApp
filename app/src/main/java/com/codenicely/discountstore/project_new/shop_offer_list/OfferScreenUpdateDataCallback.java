package com.codenicely.discountstore.project_new.shop_offer_list;


import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferScreenUpdateData;

/**
 * Created by aman on 20/10/16.
 */

public interface OfferScreenUpdateDataCallback {

    void onSuccess(OfferScreenUpdateData offerScreenUpdateData);

    void onFailure(String error);
}
