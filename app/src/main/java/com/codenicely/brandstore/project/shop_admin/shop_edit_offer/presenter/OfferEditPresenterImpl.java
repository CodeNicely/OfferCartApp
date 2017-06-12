package com.codenicely.brandstore.project.shop_admin.shop_edit_offer.presenter;

import android.net.Uri;
import android.util.Log;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.MyApplication;
import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.data.OfferEditData;
import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.model.OfferEditHelper;
import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.view.OfferEditView;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.codenicely.brandstore.project.helper.FileProvider.requestNewFile;

/**
 * Created by ujjwal on 12/5/17.
 */
public class OfferEditPresenterImpl implements OfferEditPresenter {
	OfferEditView offerEditView;
	private static final String TAG = "OfferEditPresenterImpl";

	OfferEditHelper offerEditHelper;
	private Observable<OfferEditData> offerEditDataObservable;
	private Subscription subscription;


	public OfferEditPresenterImpl(OfferEditView offerEditView, OfferEditHelper offerEditHelper) {
		this.offerEditView = offerEditView;
		this.offerEditHelper = offerEditHelper;
	}


	@Override
	public void openCamera() {
		File image = requestNewFile();

		if (offerEditView.checkPermissionForCamera()) {
			offerEditView.fileFromPath(image.getPath());
			offerEditView.showCamera();
		} else {
			if (offerEditView.requestCameraPermission()) {
				offerEditView.fileFromPath(image.getPath());
				offerEditView.showCamera();
			}
		}
	}

	@Override
	public void openGallery() {

		if (offerEditView.checkPermissionForGallery()) {

			offerEditView.showGallery();
		} else {

			if (offerEditView.requestGalleryPermission()) {
				offerEditView.showGallery();
			}
		}

	}

	@Override
	public void requestEditOfferOffer(String shop_access_token,String offer_id ,String offer_name, String offer_description
												 , int date,int month,int year ,Uri imageUri) {
		offerEditView.showDialogLoader(true);
		try {
			offerEditDataObservable = offerEditHelper.editOffer(shop_access_token,offer_id,
					offer_name, offer_description,date,month,year,imageUri);
			Log.i(TAG, "Value of Observable" + offerEditDataObservable.toString());
			subscription = offerEditDataObservable.subscribeOn(Schedulers.newThread()).
				observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OfferEditData>() {
				@Override
				public void onCompleted() {
					offerEditView.showLoader(false);
				}

				@Override
				public void onError(Throwable e) {

					offerEditView.showLoader(false);
					offerEditView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
					e.printStackTrace();
				}

				@Override
				public void onNext(OfferEditData offerEditData) {
					Log.i(TAG, "CityData " + offerEditData.toString());

					if (offerEditData.isSuccess()) {
						offerEditView.showLoader(false);
						offerEditView.showDialogLoader(false);
						offerEditView.onOfferEdited();
					} else {
						offerEditView.showLoader(false);
						offerEditView.showDialogLoader(false);
						offerEditView.showMessage(offerEditData.getMessage());
					}

				}

			});

		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
