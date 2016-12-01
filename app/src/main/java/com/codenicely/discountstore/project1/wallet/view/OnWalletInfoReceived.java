package com.codenicely.discountstore.project1.wallet.view;

import com.codenicely.discountstore.project1.wallet.model.data.WalletData;

/**
 * Created by iket on 20/10/16.
 */
public interface OnWalletInfoReceived {
    void onFailure();

    void onSuccess(WalletData walletData);
}
