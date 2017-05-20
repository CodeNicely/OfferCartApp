package com.codenicely.discountstore.project_new.shop_admin.shop_offerlist.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_admin.shop_offerlist.model.data.ShopOfferDeleteData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by aman on 18/5/17.
 */

public interface ShopOfferDeleteApi {
	@FormUrlEncoded
    @POST(Urls.REQUEST_SHOP_OFFER_DELETE)
    Call<ShopOfferDeleteData> getShopDelete(@Field("shop_access_token") String access_token, @Field("offer_id") int id);
}
