package com.codenicely.discountstore.project1.offer.view;

import com.codenicely.discountstore.project1.offer.model.data.OfferScreenDetails;
import com.codenicely.discountstore.project1.offer.model.data.OfferScreenList;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenView {

    void showMessage(String error);

    void showProgressBar(boolean show);

    void onOfferReceived(OfferScreenList offerScreenList);

    void onOfferSelected(OfferScreenDetails offerScreenDetails);

    void onOfferSent();

}