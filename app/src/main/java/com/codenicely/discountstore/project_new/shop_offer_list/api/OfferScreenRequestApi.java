package com.codenicely.discountstore.project_new.shop_offer_list.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferScreenList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 19/10/16.
 */

public interface OfferScreenRequestApi {

    @GET(Urls.MY_SHOP_OFFERS)
    Call<OfferScreenList> getCategoryListData(@Query("access_token") String access_token);

}