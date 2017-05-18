package com.codenicely.discountstore.project_new.offer_add.model;

import android.net.Uri;

import com.codenicely.discountstore.project_new.offer_add.data.OfferAddData;
import com.codenicely.discountstore.project_new.offer_edit.data.OfferEditData;

import java.io.IOException;
import java.util.Date;

import rx.Observable;

/**
 * Created by ujjwal on 18/5/17.
 */
public interface OfferAddHelper {
	Observable<OfferAddData> addOffer(String shop_access_token,
									  String offer_name,
									  String offer_description,
									  String offer_price,
									  String expiry_date,
									  Uri imageUri)throws IOException;
/*
	Observable<OfferAddData> addOffer(String shop_access_token,
									  String offer_name,
									  String offer_description,
									  String offer_price,
									  Date expiry_date,
									  Uri imageUri)throws IOException;
*/

}
