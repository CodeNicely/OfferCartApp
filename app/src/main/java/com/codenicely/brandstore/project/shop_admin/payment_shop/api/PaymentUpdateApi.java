package com.codenicely.brandstore.project.shop_admin.payment_shop.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.UpdateShopPaymentData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aman on 19/5/17.
 */

public interface PaymentUpdateApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_UPDATE_SHOP_PAYMENT)
    Call<UpdateShopPaymentData> updateShopPaymentStatusDataCall(@Field("shop_access_token") String access_token, @Field("transaction_id") String transaction_id,@Field("success")Boolean success);




}
