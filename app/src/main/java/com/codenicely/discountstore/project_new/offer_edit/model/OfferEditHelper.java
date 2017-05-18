package com.codenicely.discountstore.project_new.offer_edit.model;

import android.net.Uri;

import com.codenicely.discountstore.project_new.offer_edit.data.OfferEditData;

import java.io.IOException;

import rx.Observable;

/**
 * Created by ujjwal on 18/5/17.
 */
public interface OfferEditHelper {
	Observable<OfferEditData> editOffer(String shop_access_token,
										String offer_id,
										String offer_name,
										String offer_description,
										String offer_price,
										String expiry_date,
										Uri imageUri)throws IOException;

}
