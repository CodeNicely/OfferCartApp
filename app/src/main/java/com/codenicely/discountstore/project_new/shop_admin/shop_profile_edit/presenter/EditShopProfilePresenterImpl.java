package com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.presenter;

import android.net.Uri;
import android.util.Log;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.MyApplication;
import com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.data.ShopEditData;
import com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.model.EditShopProfileHelper;
import com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.view.EditShopProfileView;
import com.codenicely.discountstore.project_new.shop_admin.shop_register.OnPreRegistrationApiResponse;
import com.codenicely.discountstore.project_new.shop_admin.shop_register.data.ShopPreRegistrationData;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.codenicely.discountstore.project_new.helper.FileProvider.requestNewFile;

/**
 * Created by ujjwal on 17/5/17.
 */
public class EditShopProfilePresenterImpl implements EditShopProfilePresenter {
	EditShopProfileView editShopProfileView;
	EditShopProfileHelper editShopProfileHelper;
	private static final String TAG = "ShopRegisterPImpl";
	private Observable<ShopEditData> shopRegisterDataObservable;
	private Subscription subscription;

	public EditShopProfilePresenterImpl(EditShopProfileView editShopProfileView, EditShopProfileHelper editShopProfileHelper) {
		this.editShopProfileView = editShopProfileView;
		this.editShopProfileHelper = editShopProfileHelper;
	}

	@Override
	public void openCamera() {
		File image = requestNewFile();

		if (editShopProfileView.checkPermissionForCamera()) {
			editShopProfileView.fileFromPath(image.getPath());
			editShopProfileView.showCamera();
		} else {
			if (editShopProfileView.requestCameraPermission()) {
				editShopProfileView.fileFromPath(image.getPath());

				editShopProfileView.showCamera();
			}
		}

	}

	@Override
	public void openGallery() {
		if (editShopProfileView.checkPermissionForGallery()) {

			editShopProfileView.showGallery();
		} else {

			if (editShopProfileView.requestGalleryPermission()) {
				editShopProfileView.showGallery();
			}
		}

	}

	@Override
	public void editShopProfile(String access_token, String name, String description,
								String address, String category, String city, Uri imageUri) {

		editShopProfileView.showDialogLoader(true);
		try {
			shopRegisterDataObservable = editShopProfileHelper.editShop(access_token,name,description,
					address, category, city, imageUri);
			Log.i(TAG, "Value of Observable" + shopRegisterDataObservable.toString());
			subscription = shopRegisterDataObservable.subscribeOn(Schedulers.newThread()).
																								 observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ShopEditData>() {


				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {

					editShopProfileView.showLoader(false);
					editShopProfileView.showDialogLoader(false);
					editShopProfileView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
					e.printStackTrace();
				}


				@Override
				public void onNext(ShopEditData shopEditData) {

					Log.i(TAG, "Response " + shopEditData.toString());
					if (shopEditData.isSuccess()) {
						editShopProfileView.showLoader(false);
						editShopProfileView.showDialogLoader(false);
						editShopProfileView.showMessage(shopEditData.getMessage());
						editShopProfileView.onEditSuccess();
					} else {
						editShopProfileView.showLoader(false);
						editShopProfileView.showDialogLoader(false);
						editShopProfileView.showMessage(shopEditData.getMessage());
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}




	}

	@Override
	public void requestPreRegistrationDetails() {
		editShopProfileView.showLoader(true);
		editShopProfileHelper.requestPreRegistrationDetails(new OnPreRegistrationApiResponse() {
			@Override
			public void onSuccess(ShopPreRegistrationData shopPreRegistrationData) {

				if (shopPreRegistrationData.isSuccess()) {
					editShopProfileView.setPreRegistrationData(shopPreRegistrationData);
				}
				editShopProfileView.showLoader(false);
			}

			@Override
			public void onFailure(String message) {
				editShopProfileView.showLoader(false);
				editShopProfileView.showMessage(message);
			}
		});

	}
}
