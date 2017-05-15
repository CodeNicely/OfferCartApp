package com.codenicely.discountstore.project_new.shop_offer_list.presenter;


import com.codenicely.discountstore.project_new.shop_offer_list.OnBuyOffer;
import com.codenicely.discountstore.project_new.shop_offer_list.model.BuyOffer_Provider;
import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferData;
import com.codenicely.discountstore.project_new.shop_offer_list.view.BuyOfferView;

/**
 * Created by meghal on 2/11/16.
 */

public class BuyOfferPresenterImpl implements BuyOfferPresenter {

    private BuyOfferView buyOfferView;
    private BuyOffer_Provider buyOfferProvider;

    public BuyOfferPresenterImpl(BuyOfferView buyOfferView, BuyOffer_Provider buyOfferProvider) {
        this.buyOfferView = buyOfferView;
        this.buyOfferProvider = buyOfferProvider;
    }

    @Override
    public void buyOffer(int offer_id, String access_token) {

        buyOfferView.showLoadingDialog(true);
        buyOfferProvider.buyOffer(offer_id, access_token, new OnBuyOffer() {
            @Override
            public void onSuccess(OfferData buyOfferData) {

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
