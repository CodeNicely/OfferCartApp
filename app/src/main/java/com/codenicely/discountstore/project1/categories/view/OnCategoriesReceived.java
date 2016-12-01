package com.codenicely.discountstore.project1.categories.view;

import com.codenicely.discountstore.project1.categories.model.data.CategoriesList;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCategoriesReceived {
    void onFailure();

    void onSuccess(CategoriesList categoriesList);
}
