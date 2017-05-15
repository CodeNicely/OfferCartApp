package com.codenicely.discountstore.project_new.shop_offer_list.view;


import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferData;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOfferView {

    void showSnackMessage(String message);

    void showLoadingDialog(boolean show);

    void onOfferBuy(OfferData offerData);

}
