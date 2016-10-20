package com.example.aman.offercart_v1.offerscreen.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenUpdateData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by aman on 20/10/16.
 */

public interface OfferScreenResponseApi {

    @FormUrlEncoded
    @POST(Urls.REQUEST_OFFER)
    Call<OfferScreenUpdateData> sendOfferData(@Query("offer") String offer);
}
