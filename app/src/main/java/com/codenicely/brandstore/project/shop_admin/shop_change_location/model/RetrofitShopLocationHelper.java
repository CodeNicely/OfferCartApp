package com.codenicely.brandstore.project.shop_admin.shop_change_location.model;

import android.location.Location;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.ShopChangeLocationCallback;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.ShopGetLocationCallback;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.api.ShopChangeLocationApi;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.api.ShopLocationGetApi;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopChangeLocationData;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopGetLocationData;
import com.codenicely.brandstore.project.shop_admin.shop_change_password.api.ShopAdminChangePasswordApi;
import com.codenicely.brandstore.project.shop_admin.shop_change_password.data.ShopChangePasswordData;
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
 * Created by ujjwal on 7/9/17.
 */

public class RetrofitShopLocationHelper implements ShopLocationHelper {

	private ShopChangeLocationApi shopChangeLocationApi;
	private ShopLocationGetApi shopLocationGetApi;
	private Call<ShopChangeLocationData> changeLocationDataCall;
	private Call<ShopGetLocationData> getLocationDataCall;

	public RetrofitShopLocationHelper() {
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
		shopChangeLocationApi = retrofit.create(ShopChangeLocationApi.class);
		shopLocationGetApi = retrofit.create(ShopLocationGetApi.class);
	}

	@Override
	public void requestShopLocation(String access_token, final ShopGetLocationCallback shopGetLocationCallback) {
		getLocationDataCall = shopLocationGetApi.requestLocation(access_token);
		getLocationDataCall.enqueue(new Callback<ShopGetLocationData>() {
			@Override
			public void onResponse(Call<ShopGetLocationData> call, Response<ShopGetLocationData> response) {
				shopGetLocationCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<ShopGetLocationData> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}

	@Override
	public void requestChangeLocation(String shop_access_token, Double latitude, Double longitude, final ShopChangeLocationCallback shopChangeLocationCallback) {
	changeLocationDataCall =shopChangeLocationApi.requestChangeLocation(shop_access_token,latitude,longitude);
	changeLocationDataCall.enqueue(new Callback<ShopChangeLocationData>() {
		@Override
		public void onResponse(Call<ShopChangeLocationData> call, Response<ShopChangeLocationData> response) {
			shopChangeLocationCallback.onSuccess(response.body());
		}

		@Override
		public void onFailure(Call<ShopChangeLocationData> call, Throwable t) {
			t.printStackTrace();
		}
	});

	}
}
