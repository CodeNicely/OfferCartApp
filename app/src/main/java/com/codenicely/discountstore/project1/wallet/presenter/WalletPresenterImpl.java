package com.codenicely.discountstore.project1.wallet.presenter;

import com.codenicely.discountstore.project1.wallet.model.WalletProvider;
import com.codenicely.discountstore.project1.wallet.model.data.WalletData;
import com.codenicely.discountstore.project1.wallet.view.OnWalletInfoReceived;
import com.codenicely.discountstore.project1.wallet.view.WalletInterface;

/**
 * Created by iket on 20/10/16.
 */
public class WalletPresenterImpl implements WalletPresenter {
    private WalletInterface walletInterface;
    private WalletProvider walletProvider;

    public WalletPresenterImpl(WalletInterface walletInterface, WalletProvider walletProvider) {
        this.walletInterface = walletInterface;
        this.walletProvider = walletProvider;
    }

    @Override
    public void getWallet(String user_id) {
        walletInterface.showProgressbar(true);
        walletProvider.getWalletInfo(user_id, new OnWalletInfoReceived() {
            @Override
            public void onFailure() {
                walletInterface.showProgressbar(false);
                walletInterface.showMessage("Failed..Try again");
            }

            @Override
            public void onSuccess(WalletData walletData) {
                if (walletData.isSuccess()) {
                    walletInterface.showProgressbar(false);
                    walletInterface.walletReceived(walletData);
                } else {
                    walletInterface.showProgressbar(false);
                    walletInterface.showMessage(walletData.getMessage());
                }
            }
        });


    }
}
