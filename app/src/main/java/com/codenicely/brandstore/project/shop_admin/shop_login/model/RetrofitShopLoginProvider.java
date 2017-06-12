package com.codenicely.brandstore.project.shop_admin.shop_login.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_login.ShopLoginCallback;
import com.codenicely.brandstore.project.shop_admin.shop_login.api.ShopLoginApi;
import com.codenicely.brandstore.project.shop_admin.shop_login.data.ShopLoginData;
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
 * Created by ujjwal on 15/5/17.
 */
public class RetrofitShopLoginProvider implements ShopLoginProvider {

	private ShopLoginApi shopLoginApi;
	public RetrofitShopLoginProvider() {
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
		shopLoginApi = retrofit.create(ShopLoginApi.class);
	}

	@Override
	public void getLoginDetails(String mobile, String password, final ShopLoginCallback shopLoginCallback) {
		Call<ShopLoginData> shopLoginDataCall = shopLoginApi.requestLogin(mobile,password);
		shopLoginDataCall.enqueue(new Callback<ShopLoginData>() {
			@Override
			public void onResponse(Call<ShopLoginData> call, Response<ShopLoginData> response) {
				shopLoginCallback.onSuccess(response.body());

			}

			@Override
			public void onFailure(Call<ShopLoginData> call, Throwable t) {

				t.printStackTrace();
			}
		});
	}

}
