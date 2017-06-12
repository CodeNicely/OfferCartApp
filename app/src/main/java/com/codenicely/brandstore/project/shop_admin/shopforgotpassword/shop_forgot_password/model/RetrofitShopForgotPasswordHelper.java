package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.model;

import android.content.Context;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.ShopForgotPasswordCallback;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.api.ShopForgotPasswordApi;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.data.ShopForgotPasswordData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ujjwal on 1/6/17.
 */
public class RetrofitShopForgotPasswordHelper implements ShopForgotPasswordHelper {
	private Retrofit retrofit;
	private ShopForgotPasswordApi shopForgotPasswordApi;
	private Context context;
	private Call<ShopForgotPasswordData> shopForgotPasswordDataCall;

	public RetrofitShopForgotPasswordHelper(Context context) {
		this.context = context;


			Gson gson = new GsonBuilder()
								.setLenient()
								.create();
			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
			OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

			Retrofit retrofit = new Retrofit.Builder()
										.baseUrl(Urls.BASE_URL)
										.client(client)
										.addConverterFactory(GsonConverterFactory.create(gson))
										.build();

			shopForgotPasswordApi = retrofit.create(ShopForgotPasswordApi.class);

	}

	@Override
	public void getMobileNumber(String mobile, final ShopForgotPasswordCallback shopForgotPasswordCallback) {
		shopForgotPasswordDataCall =shopForgotPasswordApi.requestNewPassword(mobile);
		shopForgotPasswordDataCall.enqueue(new Callback<ShopForgotPasswordData>() {
			@Override
			public void onResponse(Call<ShopForgotPasswordData> call, Response<ShopForgotPasswordData> response) {
				shopForgotPasswordCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<ShopForgotPasswordData> call, Throwable t) {
				t.printStackTrace();
				shopForgotPasswordCallback.onFailure(t.toString());
			}
		});

	}
}
