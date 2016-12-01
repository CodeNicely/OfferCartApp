package com.codenicely.discountstore.project1.wallet.view;

import com.codenicely.discountstore.project1.wallet.model.data.WalletData;

/**
 * Created by iket on 20/10/16.
 */
public interface WalletInterface {
    void showMessage(String message);

    void showProgressbar(boolean show);

    void walletReceived(WalletData walletData);


}
