package com.example.aman.offercart_v1.wallet.model;

import com.example.aman.offercart_v1.wallet.view.OnWalletInfoReceived;

/**
 * Created by iket on 20/10/16.
 */
public interface WalletProvider {

    void getWalletInfo(String user_id, OnWalletInfoReceived onWalletInfoReceived);
}
