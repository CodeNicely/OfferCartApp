package com.codenicely.discountstore.project1.wallet.model;

import com.codenicely.discountstore.project1.wallet.view.OnWalletInfoReceived;

/**
 * Created by iket on 20/10/16.
 */
public interface WalletProvider {

    void getWalletInfo(String user_id, OnWalletInfoReceived onWalletInfoReceived);
}
