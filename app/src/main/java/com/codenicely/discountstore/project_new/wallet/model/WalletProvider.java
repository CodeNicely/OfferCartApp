package com.codenicely.discountstore.project_new.wallet.model;

import com.codenicely.discountstore.project_new.wallet.view.OnWalletInfoReceived;

/**
 * Created by iket on 20/10/16.
 */
public interface WalletProvider {

    void getWalletInfo(String access_token, OnWalletInfoReceived onWalletInfoReceived);
}
