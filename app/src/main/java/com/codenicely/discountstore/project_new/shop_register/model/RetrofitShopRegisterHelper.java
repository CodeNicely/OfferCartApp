package com.codenicely.discountstore.project_new.shop_register.model;

import android.content.Context;
import android.net.Uri;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.helper.utils.BitmapUtils;
import com.codenicely.discountstore.project_new.helper.utils.FileUtils;
import com.codenicely.discountstore.project_new.helper.utils.UriUtils;
import com.codenicely.discountstore.project_new.shop_register.api.ShopRegisterApi;
import com.codenicely.discountstore.project_new.shop_register.model.data.ShopRegisterData;

import java.io.File;
import java.io.IOException;

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
 * Created by meghal on 11/10/16.
 */

public class RetrofitShopRegisterHelper implements ShopRegisterHelper {

    private Retrofit retrofit;
    private ShopRegisterApi shopRegisterApi;
    private Context context;

    public RetrofitShopRegisterHelper(Context context) {

        this.context = context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        shopRegisterApi = retrofit.create(ShopRegisterApi.class);

    }


    @Override
    public Observable<ShopRegisterData> uploadSpot(String name, String mobile, String email, String location, String description, Uri imageUri) throws IOException {

        RequestBody name1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), name);

        RequestBody mobile1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), mobile);
        RequestBody email1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), email);
        RequestBody location1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), location);
        RequestBody description1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), description);

/*


*/
        if (imageUri != null) {
            //    File imageFile=new File(imageUri.getPath());
            File imageFile = FileUtils.BitmapToFileConverter(context, BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
            RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

            MultipartBody.Part image =
                    MultipartBody.Part.createFormData("image", imageFile.getName(), fbody);

            return shopRegisterApi.uploadSpotDetails(name1, mobile1, email1, location1, description1, image);
        }

        return null;


    }
}
