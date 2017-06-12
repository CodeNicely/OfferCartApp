package com.codenicely.brandstore.project.shop_admin.shop_profile_show.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.ShowShopProfileCallback;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.api.ShowShopProfileApi;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.data.ShowShopProfileData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ujjwal on 17/5/17.
 */
public class RetrofitShopProfileProvider implements ShowShopProfileProvider {

	ShowShopProfileApi shopProfileApi;
	Call<ShowShopProfileData> shopProfileDataCall;

	public RetrofitShopProfileProvider() {

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
//                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR).cache(RetrofitCache.provideCache()).build();
									  .build();
		Retrofit retrofit = new Retrofit.Builder()
									.baseUrl(Urls.BASE_URL)
									.client(client)
									.addConverterFactory(GsonConverterFactory.create())
									.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
									.build();
	shopProfileApi=retrofit.create(ShowShopProfileApi.class);
	}

	@Override
	public void requestShopProfileDetails(String access_token, final ShowShopProfileCallback shopProfileCallback) {
		shopProfileDataCall=shopProfileApi.getShopProfileData(access_token);
		shopProfileDataCall.enqueue(new Callback<ShowShopProfileData>() {
			@Override
			public void onResponse(Call<ShowShopProfileData> call, Response<ShowShopProfileData> response) {

				shopProfileCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<ShowShopProfileData> call, Throwable t) {
					t.printStackTrace();
					shopProfileCallback.onFailure();
			}
		});

	}

}
