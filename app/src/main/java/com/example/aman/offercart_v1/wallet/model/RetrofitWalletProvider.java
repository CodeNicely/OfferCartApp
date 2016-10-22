package com.example.aman.offercart_v1.wallet.model;

import android.util.Log;
import android.widget.Toast;

import com.example.aman.offercart_v1.cityScreen.api.CityScreenRequestApi;
import com.example.aman.offercart_v1.cityScreen.api.SendSelectedCityApi;
import com.example.aman.offercart_v1.cityScreen.models.RetrofitCityScreenProvider;
import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.wallet.api.WalletApi;
import com.example.aman.offercart_v1.wallet.model.data.WalletData;
import com.example.aman.offercart_v1.wallet.view.OnWalletInfoReceived;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iket on 20/10/16.
 */
public class RetrofitWalletProvider implements WalletProvider{

    private WalletApi walletApi;

    public RetrofitWalletProvider()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        walletApi=retrofit.create(WalletApi.class);
    }
    @Override
    public void getWalletInfo(String user_id, OnWalletInfoReceived onWalletInfoReceived) {

        Call<WalletData>call=walletApi.getWallet(user_id);
        call.enqueue(new Callback<WalletData>() {
            @Override
            public void onResponse(Call<WalletData> call, Response<WalletData> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<WalletData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
