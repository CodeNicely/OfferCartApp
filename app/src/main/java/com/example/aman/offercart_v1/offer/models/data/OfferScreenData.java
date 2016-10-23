package com.example.aman.offercart_v1.offer.models.data;

import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public class OfferScreenData
{
    private List<OfferScreenDetails> offer_data;
    private boolean success;

    public OfferScreenData(List<OfferScreenDetails> offer_data, boolean success) {
        this.offer_data = offer_data;
        this.success = success;
    }

    public List<OfferScreenDetails> getOffer_data() {
        return offer_data;
    }

    public boolean isSuccess() {
        return success;
    }
}
