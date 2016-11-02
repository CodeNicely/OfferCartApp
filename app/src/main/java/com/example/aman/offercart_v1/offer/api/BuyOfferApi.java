package com.example.aman.offercart_v1.offer.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.offer.model.data.BuyOfferData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOfferApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_BUY_OFFER)
    Call<BuyOfferData> buyOffer(@Field("offer_id") int offer_id,@Field("access_token") String access_token);

}
