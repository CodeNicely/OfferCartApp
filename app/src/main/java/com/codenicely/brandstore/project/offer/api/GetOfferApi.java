package com.codenicely.brandstore.project.offer.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.offer.model.data.OfferGetData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 8/6/17.
 */
public interface GetOfferApi {

	@FormUrlEncoded
	@POST(Urls.SUB_URL_GET_OFFER)
	Call<OfferGetData> getOffer(@Field("offer_id") int offer_id, @Field("access_token") String access_token);

}
