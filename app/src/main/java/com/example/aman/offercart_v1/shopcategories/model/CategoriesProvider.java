package com.example.aman.offercart_v1.shopcategories.model;

import com.example.aman.offercart_v1.shopcategories.view.OnCategoriesReceived;

/**
 * Created by iket on 19/10/16.
 */
public interface CategoriesProvider {
    void getCategories(OnCategoriesReceived onCategoriesReceived);
}
