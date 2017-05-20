package com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.api;

import com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.model.data.AddSubscriptionData;
import com.codenicely.discountstore.project_new.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 18/5/17.
 */

public interface AddSubscriptionDataApi {
    @GET(Urls.ADD_SUBSCRIPTION)
    Call<AddSubscriptionData>getSubscription(@Query("shop_access_token")String access_token);
}
