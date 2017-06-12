package com.codenicely.brandstore.project.shop_admin.shop_edit_offer.view;

/**
 * Created by ujjwal on 11/5/17.
 */
public interface OfferEditView {
	void showLoader(boolean show);

	void showDialogLoader(boolean show);

	void showMessage(String message);

	/**
	 * This method is for checking camera permission.
	 * Applicable only for devices with Api 23 or more.
	 *
	 * @return
	 */
	boolean checkPermissionForCamera();

	/**
	 * This method is for checking gallery permission.
	 * Applicable only for devices with api 23 or more.
	 *
	 * @return
	 */
	boolean checkPermissionForGallery();

	/**
	 * This function is for requesting camera permission if user does'nt have taken permission
	 * previously.
	 *
	 * @return
	 */
	boolean requestCameraPermission();

	/**
	 * This function is for requesting gallery permission if user does'nt have taken permission
	 * previously.
	 *
	 * @return
	 */
	boolean requestGalleryPermission();


	/**
	 * This method is called when user chooses to open camera.
	 */
	void showCamera();

	/**
	 * This method is called when user chooses to open gallery.
	 */

	void showGallery();

	void fileFromPath(String filePath);

	void showDialog(String title, String message);
	void onOfferEdited();

}
