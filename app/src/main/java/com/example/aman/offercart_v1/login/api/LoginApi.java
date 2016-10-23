package com.example.aman.offercart_v1.login.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.login.models.data.LoginData;



/**
 * Created by aman on 15/10/16.
 */
public interface LoginApi {

    @FormUrlEncoded
    @POST(Urls.SEND_OTP )
    Call<LoginData> requestLogin(@Field("name") String name, @Field("mobile") String mobile,
                                 @Field("email") String email);


}


