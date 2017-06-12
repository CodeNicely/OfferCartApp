package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.ShopChangePasswordCallback;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.api.ShopAdminChangePasswordApi;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.data.ShopChangePasswordData;
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
 * Created by ujjwal on 17/5/17.
 */
public class RetrofitShopChangePasswordHelper implements ShopChangePasswordHelper {

	private ShopAdminChangePasswordApi shopAdminChangePasswordApi;
	private Call<ShopChangePasswordData> shopChangePasswordDataCall;
	public RetrofitShopChangePasswordHelper() {
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
		shopAdminChangePasswordApi= retrofit.create(ShopAdminChangePasswordApi.class);
	}

	@Override
	public void requestChangePassword(String shop_access_token, String new_password, final ShopChangePasswordCallback shopChangePasswordCallback) {
		shopChangePasswordDataCall =shopAdminChangePasswordApi.requestChangePassword(shop_access_token,new_password);
		shopChangePasswordDataCall.enqueue(new Callback<ShopChangePasswordData>() {
			@Override
			public void onResponse(Call<ShopChangePasswordData> call, Response<ShopChangePasswordData> response) {
				shopChangePasswordCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<ShopChangePasswordData> call, Throwable t) {
				t.printStackTrace();

			}
		});
	}
}
