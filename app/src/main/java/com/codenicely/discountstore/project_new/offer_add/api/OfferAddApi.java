package com.codenicely.discountstore.project_new.offer_add.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_register.data.ShopPreRegistrationData;
import com.codenicely.discountstore.project_new.shop_register.data.ShopRegisterData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by ujjwal on 12/5/17.
 */
public interface OfferAddApi {


	@Multipart
	@POST(Urls.SUB_URL_CREATE_SHOP)
	Observable<ShopRegisterData> requestShopRegistration(
		@Part("offer_name") RequestBody name,
		@Part("validity") RequestBody mobile,
		@Part("shop_name") RequestBody password,
		@Part("description") RequestBody description,
		@Part("price") RequestBody price,
		@Part MultipartBody.Part image);




}
