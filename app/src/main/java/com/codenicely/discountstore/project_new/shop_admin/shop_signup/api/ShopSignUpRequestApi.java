package com.codenicely.discountstore.project_new.shop_admin.shop_signup.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data.ShopSignUpData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ramya on 26/2/17.
 */

public interface ShopSignUpRequestApi {
    @FormUrlEncoded
    @POST(Urls.REQUEST_SHOP_SIGNUP)
    Call<ShopSignUpData> requestShopSignUp(@Field("shop_name")String shop_name,
                                           @Field("shop_address")String shop_address,
                                           @Field("shop_image")String shop_image,
                                           @Field("shop_city_id")int shop_city_id,
                                           @Field("shop_mobile_number")String shop_mobile_number,
                                           @Field("shop_email")String shop_email,
                                           @Field("shop_password")String shop_password);
}
