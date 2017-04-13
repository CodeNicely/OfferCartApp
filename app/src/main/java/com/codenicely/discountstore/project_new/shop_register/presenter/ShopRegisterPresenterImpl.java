package com.codenicely.discountstore.project_new.shop_register.presenter;


import android.net.Uri;
import android.util.Log;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.MyApplication;
import com.codenicely.discountstore.project_new.shop_register.model.ShopRegisterHelper;
import com.codenicely.discountstore.project_new.shop_register.model.data.ShopRegisterData;
import com.codenicely.discountstore.project_new.shop_register.view.ShopRegisterView;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.codenicely.discountstore.project_new.helper.FileProvider.requestNewFile;


/**
 * Created by meghal on 11/10/16.
 */

public class ShopRegisterPresenterImpl implements ShopRegisterPresenter {


    private static final String TAG = "ShopRegisterPImpl";
    private ShopRegisterView shopRegisterView;
    private ShopRegisterHelper shopRegisterHelper;
    private Observable<ShopRegisterData> shopRegisterDataObservable;
    private Subscription subscription;

    public ShopRegisterPresenterImpl(ShopRegisterView shopRegisterView, ShopRegisterHelper shopRegisterHelper) {
        this.shopRegisterView = shopRegisterView;
        this.shopRegisterHelper = shopRegisterHelper;
    }

    @Override
    public void openCamera() {
        File image = requestNewFile();

        if (shopRegisterView.checkPermissionForCamera()) {
            shopRegisterView.fileFromPath(image.getPath());
            shopRegisterView.showCamera();
        } else {
            if (shopRegisterView.requestCameraPermission()) {
                shopRegisterView.fileFromPath(image.getPath());

                shopRegisterView.showCamera();
            }
        }

    }

    @Override
    public void openGallery() {

        if (shopRegisterView.checkPermissionForGallery()) {

            shopRegisterView.showGallery();
        } else {

            if (shopRegisterView.requestGalleryPermission()) {
                shopRegisterView.showGallery();
            }
        }

    }


    @Override
    public void uploadSpot(String name, String mobile, String email, String location, String description, Uri imageUri) {


        shopRegisterView.showLoader(true);
        try {
            shopRegisterDataObservable = shopRegisterHelper.uploadSpot(name, mobile, email, location, description, imageUri);
            Log.i(TAG, "Value of Observable" + shopRegisterDataObservable.toString());
            subscription = shopRegisterDataObservable.subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ShopRegisterData>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                    shopRegisterView.showLoader(false);
                    shopRegisterView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
                    e.printStackTrace();
                }

                @Override
                public void onNext(ShopRegisterData spotUploadData) {

                    Log.i(TAG, "Response " + spotUploadData.toString());
                    if (spotUploadData.isSuccess()) {
                        shopRegisterView.showLoader(false);
//                        shopRegisterView.showMessage(spotUploadData.getMessage());
                        shopRegisterView.showDialog("Uploaded Successfully", "Your Spot has been uploaded successfully we will be reaching you soon");
                    } else {
                        shopRegisterView.showLoader(false);
                        shopRegisterView.showMessage(spotUploadData.getMessage());
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
