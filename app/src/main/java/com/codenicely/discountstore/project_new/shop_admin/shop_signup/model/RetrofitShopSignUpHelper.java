package com.codenicely.discountstore.project_new.shop_admin.shop_signup.model;

import com.codenicely.discountstore.project_new.shop_admin.shop_signup.ShopSignUpRequestCallback;
import com.codenicely.discountstore.project_new.shop_admin.shop_signup.api.ShopSignUpRequestApi;
import com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data.ShopSignUpData;
import com.google.firebase.messaging.RemoteMessage;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramya on 26/2/17.
 */

public class RetrofitShopSignUpHelper implements ShopSignUpHelper {
    private String TAG="ShopSignUpHelper";
    private ShopSignUpRequestApi shopSignUpRequestApi;
    private Retrofit retrofit;
    public RetrofitShopSignUpHelper()
    {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit=new Retrofit.Builder().client(client).
                addConverterFactory(GsonConverterFactory.create()).build();
        shopSignUpRequestApi=retrofit.create(ShopSignUpRequestApi.class);
    }

    @Override
    public void requestSignUp(String shop_name, String shop_address, String shop_image, int shop_id, String shop_mobile_number, String shop_email, String shop_password,
                              final ShopSignUpRequestCallback shopSignUpRequestCallback) {
        Call<ShopSignUpData>call=shopSignUpRequestApi.requestShopSignUp(shop_name,shop_address,
                shop_image,shop_id,shop_mobile_number,shop_email,shop_password);
        call.enqueue(new Callback<ShopSignUpData>() {
            @Override
            public void onResponse(Call<ShopSignUpData> call, Response<ShopSignUpData> response) {
                shopSignUpRequestCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopSignUpData> call, Throwable t) {
                shopSignUpRequestCallback.onFailure(t.getMessage());
            }
        });
    }
}
