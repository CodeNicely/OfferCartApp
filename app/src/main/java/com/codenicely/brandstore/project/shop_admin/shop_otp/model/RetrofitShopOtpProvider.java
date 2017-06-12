package com.codenicely.brandstore.project.shop_admin.shop_otp.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_otp.ShopOtpCallback;
import com.codenicely.brandstore.project.shop_admin.shop_otp.api.ShopOtpApi;
import com.codenicely.brandstore.project.shop_admin.shop_otp.data.ShopOtpData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ujjwal on 14/5/17.
 */
public class RetrofitShopOtpProvider implements ShopOtpProvider {


	private ShopOtpApi shopOtpApi;


	public RetrofitShopOtpProvider() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		Retrofit retrofit = new Retrofit.Builder()
									.baseUrl(Urls.BASE_URL)
									.client(client)
									.addConverterFactory(GsonConverterFactory.create())
									.build();
		shopOtpApi = retrofit.create(ShopOtpApi.class);

	}

	@Override
	public void requestOtp(String otp, String mobile, final ShopOtpCallback shopOtpCallback) {
		Call<ShopOtpData> shopOtpDataCall = shopOtpApi.requestOtp(otp, mobile);
		shopOtpDataCall.enqueue(new Callback<ShopOtpData>() {
			@Override
			public void onResponse(Call<ShopOtpData> call, Response<ShopOtpData> response) {
				shopOtpCallback.onSuccess(response.body());

			}

			@Override
			public void onFailure(Call<ShopOtpData> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
