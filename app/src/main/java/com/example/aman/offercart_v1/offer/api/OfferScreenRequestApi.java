package com.example.aman.offercart_v1.offer.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenRequestApi {

    @GET(Urls.REQUEST_OFFER)
    Call<OfferScreenList> getCategoryListData(@Query("access_token")String access_token,@Query("shop_id") String shop_id);
}