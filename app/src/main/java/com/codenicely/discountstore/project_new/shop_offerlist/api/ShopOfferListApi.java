package com.codenicely.discountstore.project_new.shop_offerlist.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.offer.model.data.OfferScreenList;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 16/5/17.
 */

public interface ShopOfferListApi {
    @GET(Urls.REQUEST_SHOP_OFFER)
    Call<ShopOfferListData> getShopData(@Query("access_token") String access_token);
}
