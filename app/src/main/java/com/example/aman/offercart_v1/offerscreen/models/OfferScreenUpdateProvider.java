package com.example.aman.offercart_v1.offerscreen.models;

import com.example.aman.offercart_v1.offerscreen.OfferScreenUpdateDataCallback;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenUpdateData;

/**
 * Created by aman on 20/10/16.
 */

public interface OfferScreenUpdateProvider {
    void responseProductList(String offer ,OfferScreenUpdateDataCallback offerScreenUpdateDataCallback);
}
