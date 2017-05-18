package com.codenicely.discountstore.project_new.offer_add.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.offer_add.data.OfferAddData;
import com.codenicely.discountstore.project_new.offer_edit.data.OfferEditData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by ujjwal on 12/5/17.
 */
public interface OfferAddApi {


	@Multipart
	@POST(Urls.OFFER_ADD)
	Observable<OfferAddData> requestOfferAdd(
		@Part("shop_access_token") RequestBody shop_name,
		@Part("offer_title") RequestBody offer_name,
		@Part("offer_description") RequestBody description,
		@Part("expiry_date") RequestBody validity,
		@Part("offer_price") RequestBody price,
		@Part MultipartBody.Part offer_image);

}
