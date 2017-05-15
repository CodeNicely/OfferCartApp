package com.codenicely.discountstore.project_new.shop_offer_list.view;


import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferScreenList;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenView {

    void showMessage(String error);

    void showProgressBar(boolean show);

    void onOfferListReceived(OfferScreenList offerScreenList);

    //void onOfferSelected(OfferScreenDetails offerScreenDetails);

   // void onOfferSent();

}
