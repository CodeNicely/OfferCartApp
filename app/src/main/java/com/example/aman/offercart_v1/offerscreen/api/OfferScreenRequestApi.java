package com.example.aman.offercart_v1.offerscreen.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenRequestApi {

    @GET(Urls.REQUEST_OFFER)
    Call<OfferScreenData> getCategoryListData();
}
