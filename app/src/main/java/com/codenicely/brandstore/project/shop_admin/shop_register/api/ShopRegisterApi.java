package com.codenicely.brandstore.project.shop_admin.shop_register.api;


import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopPreRegistrationData;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopRegisterData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
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
    Observable<ShopRegisterData> requestShopRegistration(
            @Part("name") RequestBody name,
            @Part("mobile") RequestBody mobile,
            @Part("password") RequestBody password,
            @Part("description") RequestBody description,
            @Part("address") RequestBody address,
            @Part("category") RequestBody category,
            @Part("city") RequestBody city,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part MultipartBody.Part image);

    @GET(Urls.SUB_URL_PREREGISTER_DATA_SHOP)
    Call<ShopPreRegistrationData> requestPreRegisterData();

}
