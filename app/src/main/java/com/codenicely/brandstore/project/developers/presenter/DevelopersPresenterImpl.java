package com.codenicely.brandstore.project.developers.presenter;


import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.developers.DevelopersCallback;
import com.codenicely.brandstore.project.developers.model.DeveloperProvider;
import com.codenicely.brandstore.project.developers.model.data.DeveloperData;
import com.codenicely.brandstore.project.developers.view.DeveloperView;
import com.codenicely.brandstore.project.helper.MyApplication;

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
