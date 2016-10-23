package com.example.aman.offercart_v1.offer.view;

import com.example.aman.offercart_v1.offer.models.data.OfferScreenDetails;

import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenView {

    void showMessage(String error);
    void showProgressBar(boolean show);
    void onOfferVerified(List<OfferScreenDetails> offerScreenDetailsList);
    void onOfferSelected(int offer_id,String offer_code,String offer_name);
    void onOfferSent();

}
