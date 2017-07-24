package com.codenicely.brandstore.project.shop_admin.shop_register.providers;

import android.content.Context;
import android.net.Uri;

import com.codenicely.brandstore.project.city.api.CityRequestApi;
import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.helper.utils.BitmapUtils;
import com.codenicely.brandstore.project.helper.utils.FileUtils;
import com.codenicely.brandstore.project.helper.utils.UriUtils;
import com.codenicely.brandstore.project.shop_admin.shop_register.OnCitiesReceived;
import com.codenicely.brandstore.project.shop_admin.shop_register.OnPreRegistrationApiResponse;
import com.codenicely.brandstore.project.shop_admin.shop_register.api.ShopRegisterApi;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopPreRegistrationData;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopRegisterData;

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
 * Created by meghal on 11/10/16.
 */

public class RetrofitShopRegisterHelper implements ShopRegisterHelper {
    private CityRequestApi cityRequestApi;

    private Retrofit retrofit;
    private ShopRegisterApi shopRegisterApi;
    private Context context;

    public RetrofitShopRegisterHelper(Context context) {

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
        shopRegisterApi = retrofit.create(ShopRegisterApi.class);
        cityRequestApi = retrofit.create(CityRequestApi.class);

    }


    public Observable<ShopRegisterData> registerShop(String name,
                                                     String mobile,
                                                     String password,
                                                     String description,
                                                     String address,
                                                     String category,
                                                     String city,
                                                     Double latitude,
                                                     Double longitude,
                                                     Uri imageUri) throws IOException {

        RequestBody name1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), name);

        RequestBody mobile1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), mobile);
        RequestBody password1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), password);

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
        RequestBody longitude1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(longitude));
        RequestBody latitude1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(latitude));

/*


*/
        if (imageUri != null) {
            //    File imageFile=new File(imageUri.getPath());
            File imageFile = FileUtils.BitmapToFileConverter(context, BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
            RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

            MultipartBody.Part image =
                    MultipartBody.Part.createFormData("image", imageFile.getName(), fbody);

            return shopRegisterApi.requestShopRegistration(name1, mobile1, password1, description1,
                    address1,category1,city1,latitude1,longitude1,image);
        }

        return null;


    }

    @Override
    public void requestPreRegistrationDetails(final OnPreRegistrationApiResponse onPreRegistrationApiResponse) {

        Call<ShopPreRegistrationData> call = shopRegisterApi.requestPreRegisterData();
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
