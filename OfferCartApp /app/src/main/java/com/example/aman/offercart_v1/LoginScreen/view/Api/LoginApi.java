package com.codenicely.discountstore.project.login.view.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;
import com.codenicely.discountstore.project.helper.Urls;
import com.codenicely.discountstore.project.login.view.models.data.LoginData;




/**
 * Created by aman on 15/10/16.
 */
public interface LoginApi {


    @POST(Urls.SEND_OTP )
    Call<LoginData> requestLogin(@Query("name") String name, @Query("mobile") String mobile,
                                 @Query("email") String email);


}


