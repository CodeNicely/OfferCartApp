package com.example.aman.offercart_v1.about_us.presenter;


import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.about_us.AboutUsCallBack;
import com.example.aman.offercart_v1.about_us.model.AboutUsProvider;
import com.example.aman.offercart_v1.about_us.model.data.AboutUsData;
import com.example.aman.offercart_v1.about_us.view.AboutUsView;
import com.example.aman.offercart_v1.helper.MyApplication;

/**
 * Created by meghal on 13/10/16.
 */

public class AboutUsPresenterImpl implements AboutUsPresenter{


    private AboutUsView aboutUsView;
    private AboutUsProvider aboutUsProvider;

    public AboutUsPresenterImpl(AboutUsView aboutUsView, AboutUsProvider aboutUsProvider) {
        this.aboutUsView = aboutUsView;
        this.aboutUsProvider = aboutUsProvider;
    }

    @Override
    public void requestAboutUs() {

        aboutUsView.showLoader(true);
        aboutUsProvider.requestAboutUs(new AboutUsCallBack() {
            @Override
            public void onSuccess(AboutUsData aboutUsData) {

                aboutUsView.showLoader(false);
                if(aboutUsData.isSuccess()){

                    aboutUsView.setData(aboutUsData);

                }else{
                    aboutUsView.showMessage(aboutUsData.getMessage());
                }
            }
            @Override
            public void onFailure() {

                aboutUsView.showLoader(false);
                aboutUsView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));

            }
        });

    }

    @Override
    public void onDestroy() {
        aboutUsProvider.onDestroy();
    }
}
