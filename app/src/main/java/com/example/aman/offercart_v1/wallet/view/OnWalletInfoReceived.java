package com.example.aman.offercart_v1.wallet.view;

import com.example.aman.offercart_v1.wallet.model.data.WalletData;

/**
 * Created by iket on 20/10/16.
 */
public interface OnWalletInfoReceived {
    void onFailure();
    void onSuccess(WalletData walletData);
}
