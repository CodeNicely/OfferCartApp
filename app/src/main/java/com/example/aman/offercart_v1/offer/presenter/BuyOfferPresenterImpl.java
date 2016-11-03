package com.example.aman.offercart_v1.offer.presenter;

import com.example.aman.offercart_v1.offer.OnBuyOffer;
import com.example.aman.offercart_v1.offer.model.BuyOfferProvider;
import com.example.aman.offercart_v1.offer.model.data.BuyOfferData;
import com.example.aman.offercart_v1.offer.view.BuyOfferView;

/**
 * Created by meghal on 2/11/16.
 */

public class BuyOfferPresenterImpl implements BuyOfferPresenter {

    private BuyOfferView buyOfferView;
    private BuyOfferProvider buyOfferProvider;

    public BuyOfferPresenterImpl(BuyOfferView buyOfferView, BuyOfferProvider buyOfferProvider) {
        this.buyOfferView = buyOfferView;
        this.buyOfferProvider = buyOfferProvider;
    }

    @Override
    public void buyOffer(int offer_id, String access_token) {

        buyOfferView.showLoadingDialog(true);
        buyOfferProvider.buyOffer(offer_id, access_token, new OnBuyOffer() {
            @Override
            public void onSuccess(BuyOfferData buyOfferData) {

                buyOfferView.showLoadingDialog(false);
                if (buyOfferData.isSuccess()) {
                //    buyOfferView.showSnackMessage(buyOfferData.getMessage());
                    buyOfferView.onOfferBuy(buyOfferData);
                } else {
                  //  buyOfferView.showSnackMessage(buyOfferData.getMessage());
                    buyOfferView.onOfferBuy(buyOfferData);

                }
            }

            @Override
            public void onFailure() {

                buyOfferView.showLoadingDialog(false);
                buyOfferView.showSnackMessage("Unable to connect to server ! Please check your Internet Connection.");
            }
        });

    }
}
