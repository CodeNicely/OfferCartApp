package com.codenicely.discountstore.project.wallet.model;

import com.codenicely.discountstore.project.wallet.view.OnWalletInfoReceived;

/**
 * Created by iket on 20/10/16.
 */
public interface WalletProvider {

    void getWalletInfo(String user_id, OnWalletInfoReceived onWalletInfoReceived);
}
