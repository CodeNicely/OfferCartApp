package com.codenicely.brandstore.project.shop_admin.shop_edit_offer.model;

import android.net.Uri;

import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.data.OfferEditData;

import java.io.IOException;

import rx.Observable;

/**
 * Created by ujjwal on 18/5/17.
 */
public interface OfferEditHelper {
	Observable<OfferEditData> editOffer(String shop_access_token,String offer_id, String offer_name, String offer_description,
										int date,int month,int year,Uri imageUri)throws IOException;

}
