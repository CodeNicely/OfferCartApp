package com.example.aman.offercart_v1.categories.model;

import com.example.aman.offercart_v1.categories.view.OnCategoriesReceived;

/**
 * Created by iket on 19/10/16.
 */
public interface CategoriesProvider {
    void getCategories(String access_token, OnCategoriesReceived onCategoriesReceived);
}
