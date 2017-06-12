package com.codenicely.brandstore.project.shop_admin.shop_register.presenter;


import android.net.Uri;
import android.util.Log;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.city.data.CityData;
import com.codenicely.brandstore.project.helper.MyApplication;
import com.codenicely.brandstore.project.shop_admin.shop_register.OnCitiesReceived;
import com.codenicely.brandstore.project.shop_admin.shop_register.OnPreRegistrationApiResponse;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopPreRegistrationData;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopRegisterData;
import com.codenicely.brandstore.project.shop_admin.shop_register.providers.ShopRegisterHelper;
import com.codenicely.brandstore.project.shop_admin.shop_register.view.ShopRegisterView;

import java.io.File;
import java.io.IOException;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.codenicely.brandstore.project.helper.FileProvider.requestNewFile;


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
            }else {

			}
        }

    }


    @Override
    public void registerShop(String name, String mobile, String password,
                             String description, String address, String category,
                             String city,Double latitude,Double longitude, Uri imageUri) {


        shopRegisterView.showDialogLoader(true);
        try {
            shopRegisterDataObservable = shopRegisterHelper.registerShop(name,
                    mobile, password, description, address, category, city,latitude,longitude, imageUri);
            Log.i(TAG, "Value of Observable" + shopRegisterDataObservable.toString());
            subscription = shopRegisterDataObservable.subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ShopRegisterData>() {

                @Override
                public void onCompleted() {
                    shopRegisterView.showLoader(false);
                }

                @Override
                public void onError(Throwable e) {

                    shopRegisterView.showLoader(false);
                    shopRegisterView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
                    e.printStackTrace();
                }

                @Override
                public void onNext(ShopRegisterData spotUploadData) {

                    Log.i(TAG, "CityData " + spotUploadData.toString());
                    if (spotUploadData.isSuccess()) {
                        shopRegisterView.onRegistrationSuccess();

                    } else {
						//shopRegisterView.onRegistrationSuccess();
                    }
                    shopRegisterView.showMessage(spotUploadData.getMessage());
                    shopRegisterView.showDialogLoader(false);
                    shopRegisterView.showLoader(false);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestPreRegistrationDetails() {
        shopRegisterView.showLoader(true);
        shopRegisterHelper.requestPreRegistrationDetails(new OnPreRegistrationApiResponse() {
            @Override
            public void onSuccess(ShopPreRegistrationData shopPreRegistrationData) {

                if (shopPreRegistrationData.isSuccess()) {
                    shopRegisterView.setPreRegistrationData(shopPreRegistrationData);
                }
                shopRegisterView.showLoader(false);
            }

            @Override
            public void onFailure(String message) {
                shopRegisterView.showLoader(false);
                shopRegisterView.showMessage(message);
            }
        });


    }

    @Override
    public void requestCityList(int state_id) {
        shopRegisterView.showLoader(true);
        shopRegisterHelper.requestCityList(state_id, new OnCitiesReceived() {
            @Override
            public void onFailure() {
                shopRegisterView.showLoader(false);
                shopRegisterView.showMessage("Failed to get City List");
            }

            @Override
            public void onSuccess(CityData cityData) {
                shopRegisterView.showLoader(false);
                shopRegisterView.onCitiesRecieved(cityData.getCity_data());


            }
        });
    }


}
