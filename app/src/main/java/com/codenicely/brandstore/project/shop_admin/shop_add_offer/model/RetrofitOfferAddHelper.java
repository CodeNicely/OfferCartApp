package com.codenicely.brandstore.project.shop_admin.shop_add_offer.model;

import android.content.Context;
import android.net.Uri;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.helper.utils.BitmapUtils;
import com.codenicely.brandstore.project.helper.utils.FileUtils;
import com.codenicely.brandstore.project.helper.utils.UriUtils;
import com.codenicely.brandstore.project.shop_admin.shop_add_offer.api.OfferAddApi;
import com.codenicely.brandstore.project.shop_admin.shop_add_offer.data.OfferAddData;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by ujjwal on 18/5/17.
 */
public class RetrofitOfferAddHelper implements OfferAddHelper{
	private Retrofit retrofit;
	private OfferAddApi offerAddApi;
	private Context context;

	public RetrofitOfferAddHelper(Context context) {
		this.context = context;
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(10, TimeUnit.MINUTES).
	    writeTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).retryOnConnectionFailure(true).build();


		retrofit = new Retrofit.Builder()
						   .baseUrl(Urls.BASE_URL)
						   .client(client)
						   .addConverterFactory(GsonConverterFactory.create())
						   .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
						   .build();
		offerAddApi =retrofit.create(OfferAddApi.class);
	}


	@Override
	public Observable<OfferAddData> addOffer(String shop_access_token, String offer_name, String offer_description,
											 int date,int month,int year, Uri imageUri) throws IOException {

		String year2,date2,month2,offer_price2;

		year2= String.valueOf(year);
		month2= String.valueOf(month);
		date2= String.valueOf(date);
	//	offer_price2= String.valueOf(offer_price);

		RequestBody shop_access_token1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), shop_access_token);

		RequestBody offer_name1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), offer_name);
		RequestBody offer_description1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), offer_description);

/*
		RequestBody offer_price1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), offer_price2);
*/
		RequestBody date1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), date2);
		RequestBody month1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), month2);
		RequestBody year1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), year2);



		if (imageUri != null) {
			//    File imageFile=new File(imageUri.getPath());
			File imageFile = FileUtils.BitmapToFileConverter(context, BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
			RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

			MultipartBody.Part image =
					MultipartBody.Part.createFormData("offer_image", imageFile.getName(), fbody);

			return offerAddApi.requestOfferAdd(shop_access_token1, offer_name1, offer_description1,
				date1,month1,year1,image);
		}


			return null;
	}
}
