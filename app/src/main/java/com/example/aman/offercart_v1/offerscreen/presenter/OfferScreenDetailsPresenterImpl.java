package com.example.aman.offercart_v1.offerscreen.presenter;

import com.example.aman.offercart_v1.offerscreen.OfferScreenDetailsCallback;
import com.example.aman.offercart_v1.offerscreen.OfferScreenUpdateDataCallback;
import com.example.aman.offercart_v1.offerscreen.models.OfferScreenDetailsProvider;
import com.example.aman.offercart_v1.offerscreen.models.OfferScreenUpdateProvider;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenData;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenUpdateData;
import com.example.aman.offercart_v1.offerscreen.view.OfferScreenView;

/**
 * Created by aman on 19/10/16.
 */

public class OfferScreenDetailsPresenterImpl implements OfferScreenDetailsPresenter {

    private OfferScreenView offerScreenView;
    private OfferScreenUpdateProvider offerScreenUpdateProvider;
    private OfferScreenDetailsProvider offerScreenDetailsProvider;

    public OfferScreenDetailsPresenterImpl(OfferScreenView offerScreenView,
                                           OfferScreenUpdateProvider offerScreenUpdateProvider,
                                           OfferScreenDetailsProvider offerScreenDetailsProvider)
    {
        this.offerScreenView = offerScreenView;
        this.offerScreenUpdateProvider = offerScreenUpdateProvider;
        this.offerScreenDetailsProvider = offerScreenDetailsProvider;
    }

    @Override
    public void requestOfferList() {
        offerScreenView.showProgressBar(true);
        offerScreenDetailsProvider.requestOfferList(new OfferScreenDetailsCallback() {
            @Override
            public void onSuccess(OfferScreenData offerScreenData) {
                offerScreenView.showProgressBar(false);
                offerScreenView.onOfferVerified(offerScreenData.getOffer_data());

            }

            @Override
            public void onFailure(String error) {
                offerScreenView.showProgressBar(false);
                offerScreenView.showMessage("Please try again in sometime.");

            }
        });


    }

    @Override
    public void responseOfferList(String offer_id) {
        offerScreenView.showProgressBar(true);
        offerScreenUpdateProvider.responseOfferList(offer_id,new OfferScreenUpdateDataCallback() {
            @Override
            public void onSuccess(OfferScreenUpdateData offerScreenUpdateData) {
                offerScreenView.showMessage("Done");
                offerScreenView.showProgressBar(false);
                offerScreenView.onOfferSent();
            }

            @Override
            public void onFailure(String error) {
                offerScreenView.showProgressBar(false);
                offerScreenView.showMessage("Please try again in sometime.");

            }
        });


    }
}
