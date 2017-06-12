package com.codenicely.brandstore.project.categories.view;

import com.codenicely.brandstore.project.categories.model.data.CategoriesList;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCategoriesReceived {
    void onFailure();

    void onSuccess(CategoriesList categoriesList);
}
