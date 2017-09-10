package com.codenicely.brandstore.project.offer.presenter;

import com.codenicely.brandstore.project.offer.OfferScreenDetailsCallback;
import com.codenicely.brandstore.project.offer.model.OfferScreenDetailsProvider;
import com.codenicely.brandstore.project.offer.model.data.OfferData;
import com.codenicely.brandstore.project.offer.view.OfferScreenView;

/**
 * Created by aman on 19/10/16.
 */

public class OfferScreenDetailsPresenterImpl implements OfferScreenDetailsPresenter {

    private OfferScreenView offerScreenView;
    private OfferScreenDetailsProvider offerScreenDetailsProvider;


    public OfferScreenDetailsPresenterImpl(OfferScreenView offerScreenView,
                                           OfferScreenDetailsProvider offerScreenDetailsProvider) {
        this.offerScreenView = offerScreenView;
        this.offerScreenDetailsProvider = offerScreenDetailsProvider;
    }

    @Override
    public void requestOfferList(String access_token, int shop_id) {
        offerScreenView.showProgressBar(true);
        offerScreenDetailsProvider.requestOfferList(access_token, shop_id, new OfferScreenDetailsCallback() {
            @Override
            public void onSuccess(OfferData offerScreenData) {
                offerScreenView.showProgressBar(false);
                if (offerScreenData.isSuccess()) {
                    offerScreenView.onOfferReceived(offerScreenData);
                } else {
                    offerScreenView.showMessage(offerScreenData.getMessage());
                }
            }

            @Override
            public void onFailure(String error) {
                offerScreenView.showProgressBar(false);
                offerScreenView.showMessage("Please try again in sometime.");

            }
        });


    }

//    @Override
//    public void responseOfferList(String offer_id) {
//        offerScreenView.showProgressBar(true);
//        offerScreenUpdateProvider.responseOfferList(offer_id,new OfferScreenUpdateDataCallback() {
//            @Override
//            public void onSuccess(OfferScreenUpdateData offerScreenUpdateData) {
//                offerScreenView.showMessage("Done");
//                offerScreenView.showProgressBar(false);
//                offerScreenView.onOfferSent();
//            }
//
//            @Override
//            public void onFailure(String error) {
//                offerScreenView.showProgressBar(false);
//                offerScreenView.showMessage("Please try again in sometime.");
//
//            }
//        });
//
//
//    }
}
