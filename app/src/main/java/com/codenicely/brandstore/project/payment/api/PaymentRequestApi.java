package com.codenicely.brandstore.project.payment.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.payment.model.data.PaymentData;

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
