package com.example.aman.offercart_v1.categories.view;

import com.example.aman.offercart_v1.categories.model.data.CategoriesList;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCategoriesReceived {
    void onFailure();
    void onSuccess(CategoriesList categoriesList);
}
