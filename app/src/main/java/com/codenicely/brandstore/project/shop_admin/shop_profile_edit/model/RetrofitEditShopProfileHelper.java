package com.codenicely.brandstore.project.shop_admin.shop_profile_edit.model;

import android.content.Context;
import android.net.Uri;

import com.codenicely.brandstore.project.city.api.CityRequestApi;
import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.helper.utils.BitmapUtils;
import com.codenicely.brandstore.project.helper.utils.FileUtils;
import com.codenicely.brandstore.project.helper.utils.UriUtils;
import com.codenicely.brandstore.project.shop_admin.shop_profile_edit.api.EditShopProfileAPI;
import com.codenicely.brandstore.project.shop_admin.shop_profile_edit.data.ShopEditData;
import com.codenicely.brandstore.project.shop_admin.shop_register.OnCitiesReceived;
import com.codenicely.brandstore.project.shop_admin.shop_register.OnPreRegistrationApiResponse;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopPreRegistrationData;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by ujjwal on 17/5/17.
 */
public class RetrofitEditShopProfileHelper implements EditShopProfileHelper {
	private Retrofit retrofit;
	private EditShopProfileAPI editShopProfileAPI;
	private Context context;
	private CityRequestApi cityRequestApi;



	public RetrofitEditShopProfileHelper(Context context) {
		this.context = context;
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(10, TimeUnit.MINUTES).
			writeTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).retryOnConnectionFailure(true)
			  .build();


		retrofit = new Retrofit.Builder()
						   .baseUrl(Urls.BASE_URL)
						   .client(client)
						   .addConverterFactory(GsonConverterFactory.create())
						   .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
						   .build();
		editShopProfileAPI= retrofit.create(EditShopProfileAPI.class);
		cityRequestApi = retrofit.create(CityRequestApi.class);
	}

	@Override
	public Observable<ShopEditData> editShop(String shop_access_token, String name,
											 String description, String address, String category,
											 String city,Double latitude,Double longitude, Uri imageUri) throws IOException {

		RequestBody shop_access_token1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), shop_access_token);

		RequestBody name1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), name);
		RequestBody description1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), description);
		RequestBody address1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), address);
		RequestBody category1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), category);
		RequestBody city1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), city);
		RequestBody latitude1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), latitude+"");
		RequestBody longitude1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), longitude+"");
			if (imageUri != null) {
			//    File imageFile=new File(imageUri.getPath());
			File imageFile = FileUtils.BitmapToFileConverter(context, BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
			RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

			MultipartBody.Part image =
					MultipartBody.Part.createFormData("image", imageFile.getName(), fbody);

			return editShopProfileAPI.requestShopEdit(shop_access_token1,name1, description1,
					address1,category1,city1,latitude1,longitude1,image);
		}else {
				MultipartBody.Part image =null;
				return editShopProfileAPI.requestShopEdit(shop_access_token1,name1, description1,
						address1,category1,city1,latitude1,longitude1,image);
			}
	}

	@Override
	public void requestPreRegistrationDetails(final OnPreRegistrationApiResponse onPreRegistrationApiResponse) {

		Call<ShopPreRegistrationData> call = editShopProfileAPI.requestPreRegisterData();
		call.enqueue(new Callback<ShopPreRegistrationData>() {
			@Override
			public void onResponse(Call<ShopPreRegistrationData> call, Response<ShopPreRegistrationData> response) {
				onPreRegistrationApiResponse.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<ShopPreRegistrationData> call, Throwable t) {
				t.printStackTrace();
				onPreRegistrationApiResponse.onFailure(t.getMessage());
			}
		});

	}

	@Override
	public void requestCityList(int state_id, final OnCitiesReceived onCitiesReceived) {
		Call<com.codenicely.brandstore.project.city.data.CityData> call = cityRequestApi.getCities("No access token needed",state_id);
		call.enqueue(new Callback<com.codenicely.brandstore.project.city.data.CityData>() {
			@Override
			public void onResponse(Call<com.codenicely.brandstore.project.city.data.CityData> call, retrofit2.Response<com.codenicely.brandstore.project.city.data.CityData> response) {
				onCitiesReceived.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<com.codenicely.brandstore.project.city.data.CityData> call, Throwable t) {
				t.printStackTrace();
				onCitiesReceived.onFailure();

			}
		});

	}
}
