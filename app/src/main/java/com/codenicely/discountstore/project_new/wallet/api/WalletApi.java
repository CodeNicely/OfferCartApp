package com.codenicely.discountstore.project_new.wallet.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.wallet.model.data.WalletData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iket on 20/10/16.
 */
public interface WalletApi {
    @GET(Urls.WALLET)
    Call<WalletData> getWallet(@Query("access_token") String access_token);
}
