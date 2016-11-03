package com.example.aman.offercart_v1.offer.view;

import com.example.aman.offercart_v1.offer.model.data.BuyOfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOfferView {


    void showSnackMessage(String message);

    void showLoadingDialog(boolean show);

    void onOfferBuy(BuyOfferData buyOfferData);

}
