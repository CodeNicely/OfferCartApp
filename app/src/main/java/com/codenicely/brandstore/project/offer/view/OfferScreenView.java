package com.codenicely.brandstore.project.offer.view;

import com.codenicely.brandstore.project.offer.model.data.OfferScreenDetails;
import com.codenicely.brandstore.project.offer.model.data.OfferData;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenView {

    void showMessage(String error);

    void showProgressBar(boolean show);

    void onOfferReceived(OfferData offerScreenList);

    void onOfferSelected(OfferScreenDetails offerScreenDetails);

    void onOfferSent();

}
