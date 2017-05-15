package com.codenicely.discountstore.project_new.shop_offer_list;


import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferScreenList;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsCallback {
    void onSuccess(OfferScreenList offerScreenData);

    void onFailure(String error);

}
