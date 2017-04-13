package com.codenicely.discountstore.project_new.shop_register.api;


import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_register.model.data.ShopRegisterData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by meghal on 12/10/16.
 */

public interface ShopRegisterApi {

    @Multipart
    @POST(Urls.SUB_URL_CREATE_SHOP)
    Observable<ShopRegisterData> uploadSpotDetails(@Part("name") RequestBody name, @Part("mobile") RequestBody mobile
            , @Part("email") RequestBody email, @Part("location") RequestBody location, @Part("description") RequestBody description, @Part MultipartBody.Part image);
}
