package com.example.aman.offercart_v1.developers.presenter;


import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.developers.DevelopersCallback;
import com.example.aman.offercart_v1.developers.model.DeveloperProvider;
import com.example.aman.offercart_v1.developers.model.data.DeveloperData;
import com.example.aman.offercart_v1.developers.view.DeveloperView;
import com.example.aman.offercart_v1.helper.MyApplication;

/**
 * Created by meghal on 17/10/16.
 */

public class DevelopersPresenterImpl implements DevelopersPresenter {

    private DeveloperView developerView;
    private DeveloperProvider developerProvider;

    public DevelopersPresenterImpl(DeveloperView developerView, DeveloperProvider developerProvider) {
        this.developerView = developerView;
        this.developerProvider = developerProvider;
    }

    @Override
    public void requestDevelopersData() {

        developerView.showLoading(true);
        developerProvider.requestDevelopersData(new DevelopersCallback() {
            @Override
            public void onSuccess(DeveloperData developerData) {

                developerView.showLoading(false);
                if (developerData.isSuccess()) {
                    developerView.setData(developerData.getCompanyData());
                } else {
                    developerView.showMessage(developerData.getMessage());
                }

            }

            @Override
            public void onFailed() {

                developerView.showLoading(false);
                developerView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
            }
        });


    }

    @Override
    public void onDestroy() {
        developerProvider.onDestroy();
    }
}
