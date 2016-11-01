package com.example.aman.offercart_v1.offer.view;

import com.example.aman.offercart_v1.offer.model.data.OfferScreenDetails;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenList;

import java.util.List;

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
