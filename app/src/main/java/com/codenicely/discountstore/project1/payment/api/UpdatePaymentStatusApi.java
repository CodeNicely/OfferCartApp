package com.codenicely.discountstore.project1.payment.api;

import com.codenicely.discountstore.project1.helper.Urls;
import com.codenicely.discountstore.project1.payment.model.data.UpdatePaymentStatusData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by meghal on 10/12/16.
 */

public interface UpdatePaymentStatusApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_UPDATE_PAYMENT_STATUS)
    Call<UpdatePaymentStatusData> updatePaymentStatusDataCall(@Field("access_token") String access_token, @Field("transaction_id") String transaction_id);


}
