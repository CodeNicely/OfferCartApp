package com.codenicely.brandstore.project.wallet.view;

import com.codenicely.brandstore.project.wallet.model.data.WalletData;

/**
 * Created by iket on 20/10/16.
 */
public interface OnWalletInfoReceived {
    void onFailure();

    void onSuccess(WalletData walletData);
}
