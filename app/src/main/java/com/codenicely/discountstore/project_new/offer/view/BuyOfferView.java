package com.codenicely.discountstore.project_new.offer.view;

import com.codenicely.discountstore.project_new.offer.model.data.OfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOfferView {


    void showSnackMessage(String message);

    void showLoadingDialog(boolean show);

    void onOfferBuy(OfferData offerData);

}
