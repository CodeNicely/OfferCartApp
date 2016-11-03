package com.codenicely.discountstore.project.offer.view;

import com.codenicely.discountstore.project.offer.model.data.OfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOfferView {


    void showSnackMessage(String message);

    void showLoadingDialog(boolean show);

    void onOfferBuy(OfferData offerData);

}
