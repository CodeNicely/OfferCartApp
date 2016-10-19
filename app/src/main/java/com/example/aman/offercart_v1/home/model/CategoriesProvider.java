package com.example.aman.offercart_v1.home.model;

import com.example.aman.offercart_v1.home.view.OnCategoriesReceived;

/**
 * Created by iket on 19/10/16.
 */
public interface CategoriesProvider {
    void getCategories(OnCategoriesReceived onCategoriesReceived);
}
