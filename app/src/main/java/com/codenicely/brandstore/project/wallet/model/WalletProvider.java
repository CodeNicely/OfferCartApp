package com.codenicely.brandstore.project.wallet.model;

import com.codenicely.brandstore.project.wallet.view.OnWalletInfoReceived;

/**
 * Created by iket on 20/10/16.
 */
public interface WalletProvider {

    void getWalletInfo(String access_token, OnWalletInfoReceived onWalletInfoReceived);
}
