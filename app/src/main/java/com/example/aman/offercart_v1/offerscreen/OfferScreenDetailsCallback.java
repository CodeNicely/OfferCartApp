package com.example.aman.offercart_v1.offerscreen;

import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenData;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenDetails;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenDetailsCallback
{
    void onSuccess(OfferScreenData offerScreenData);
    void onFailure(String error);

}
