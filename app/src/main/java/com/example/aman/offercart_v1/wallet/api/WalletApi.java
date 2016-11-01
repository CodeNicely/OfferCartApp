package com.example.aman.offercart_v1.wallet.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.wallet.model.data.WalletData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iket on 20/10/16.
 */
public interface WalletApi {
    @GET(Urls.WALLET)
    Call<WalletData>getWallet(@Query("user_id") String user_id);
}
