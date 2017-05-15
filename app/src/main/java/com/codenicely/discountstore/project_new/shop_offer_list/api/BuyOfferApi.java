package com.codenicely.discountstore.project_new.shop_offer_list.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferData;

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
    Call<OfferData> buyOffer(@Field("offer_id") int offer_id, @Field("access_token") String access_token);

}
