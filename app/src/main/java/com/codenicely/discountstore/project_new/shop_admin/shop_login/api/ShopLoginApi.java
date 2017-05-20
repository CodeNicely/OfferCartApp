package com.codenicely.discountstore.project_new.shop_admin.shop_login.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_admin.shop_login.data.ShopLoginData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 15/5/17.
 */
public interface ShopLoginApi {
	@FormUrlEncoded
	@POST(Urls.VERIFY_SHOP_LOGIN)
	Call<ShopLoginData> requestLogin(@Field("mobile") String mobile, @Field("password") String password);
}
