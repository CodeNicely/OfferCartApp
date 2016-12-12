package com.codenicely.discountstore.project1.payment.api;

import com.codenicely.discountstore.project1.helper.Urls;
import com.codenicely.discountstore.project1.payment.model.data.PaymentData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by meghal on 1/12/16.
 */

public interface PaymentRequestApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_PAYMENT_HASH)
    Call<PaymentData> getPaymentHash(@Field("amount") Double amount, @Field("access_token") String access_token);

}
