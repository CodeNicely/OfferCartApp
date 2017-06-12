package com.codenicely.brandstore.project.shop_admin.shop_add_offer.presenter;

import android.net.Uri;
import android.util.Log;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.MyApplication;
import com.codenicely.brandstore.project.shop_admin.shop_add_offer.data.OfferAddData;
import com.codenicely.brandstore.project.shop_admin.shop_add_offer.model.OfferAddHelper;
import com.codenicely.brandstore.project.shop_admin.shop_add_offer.view.OfferAddView;

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
public class OfferAddPresenterImpl implements OfferAddPresenter {
	OfferAddView offerAddView;
	private static final String TAG = "OfferEditPresenterImpl";

	OfferAddHelper offerAddHelper;
	private Observable<OfferAddData> offerAddDataObservable;
	private Subscription subscription;


	public OfferAddPresenterImpl(OfferAddView offerAddView, OfferAddHelper offerAddHelper) {
		this.offerAddView = offerAddView;
		this.offerAddHelper = offerAddHelper;
	}


	@Override
	public void openCamera() {
		File image = requestNewFile();

		if (offerAddView.checkPermissionForCamera()) {
			offerAddView.fileFromPath(image.getPath());
			offerAddView.showCamera();
		} else {
			if (offerAddView.requestCameraPermission()) {
				offerAddView.fileFromPath(image.getPath());
				offerAddView.showCamera();
			}
		}
	}

	@Override
	public void openGallery() {

		if (offerAddView.checkPermissionForGallery()) {

			offerAddView.showGallery();
		} else {

			if (offerAddView.requestGalleryPermission()) {
				offerAddView.showGallery();
			}
		}

	}

	@Override
	public void addOffer(String shop_access_token, String offer_name, String offer_description,
						 int date,int month,int year, Uri imageUri) {
		offerAddView.showDialogLoader(true);
		try {
			offerAddDataObservable = offerAddHelper.addOffer(shop_access_token,
					offer_name, offer_description,date,month,year,imageUri);
			Log.i(TAG, "Value of Observable" + offerAddDataObservable.toString());
			subscription = offerAddDataObservable.subscribeOn(Schedulers.newThread()).
			 observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OfferAddData>() {
				@Override
				public void onCompleted() {
					offerAddView.showLoader(false);
				}

				@Override
				public void onError(Throwable e) {

					offerAddView.showLoader(false);
					offerAddView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
					e.printStackTrace();
				}

				@Override
				public void onNext(OfferAddData offerAddData) {
					Log.i(TAG, "CityData " + offerAddData.toString());

					if (offerAddData.isSuccess()) {
						offerAddView.showLoader(false);
						offerAddView.showDialogLoader(false);
						offerAddView.onOfferAdded();
					} else {
						offerAddView.showLoader(false);
						offerAddView.showDialogLoader(false);
						offerAddView.showMessage(offerAddData.getMessage());
					}
				}

			});

		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
