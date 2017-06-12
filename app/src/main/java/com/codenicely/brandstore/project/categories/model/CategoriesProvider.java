package com.codenicely.brandstore.project.categories.model;

import com.codenicely.brandstore.project.categories.view.OnCategoriesReceived;

/**
 * Created by iket on 19/10/16.
 */
public interface CategoriesProvider {
    void getCategories(String access_token, OnCategoriesReceived onCategoriesReceived);
}
