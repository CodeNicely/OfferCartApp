package com.codenicely.discountstore.project_new.offer.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.offer.model.data.OfferData;

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
	Call<OfferData> getOffer(@Field("offer_id") int offer_id, @Field("access_token") String access_token);

}
