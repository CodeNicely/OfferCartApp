package com.example.aman.offercart_v1.offerscreen.view;

import com.example.aman.offercart_v1.WelcomeScreen.models.data.WelcomeImageDetails;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenDetails;

import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenView {

    void showMessage(String error);
    void showProgressBar(boolean show);
    void setData(List<OfferScreenDetails> offerScreenDetails);
}
