package com.codenicely.brandstore.project.offer.view;

import com.codenicely.brandstore.project.offer.model.data.OfferGetData;

/**
 * Created by meghal on 2/11/16.
 */

public interface GetOfferView {


    void showSnackMessage(String message);

    void showLoadingDialog(boolean show);

    void onOfferget(OfferGetData offerData);

}
