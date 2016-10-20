package com.example.aman.offercart_v1.wallet.presenter;

import com.example.aman.offercart_v1.wallet.model.WalletProvider;
import com.example.aman.offercart_v1.wallet.model.data.WalletData;
import com.example.aman.offercart_v1.wallet.view.OnWalletInfoReceived;
import com.example.aman.offercart_v1.wallet.view.WalletInterface;

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
                walletInterface.showProgressbar(false);
                walletInterface.walletReceived(walletData);
            }
        });


    }
}
