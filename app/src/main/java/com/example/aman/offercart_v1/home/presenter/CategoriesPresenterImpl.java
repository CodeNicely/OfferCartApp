package com.example.aman.offercart_v1.home.presenter;

import com.example.aman.offercart_v1.home.model.CategoriesProvider;
import com.example.aman.offercart_v1.home.model.data.CategoryData;
import com.example.aman.offercart_v1.home.view.CategoriesView;
import com.example.aman.offercart_v1.home.view.OnCategoriesReceived;

import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class CategoriesPresenterImpl implements CategoriesPresenter {
    private CategoriesView categoriesView;

    public CategoriesPresenterImpl(CategoriesView categoriesView, CategoriesProvider categoriesProvider) {
        this.categoriesView = categoriesView;
        this.categoriesProvider = categoriesProvider;
    }

    private CategoriesProvider categoriesProvider;




    @Override
    public void getCategories() {

        categoriesView.showProgressbar(true);

        categoriesProvider.getCategories(new OnCategoriesReceived() {
            @Override
            public void onFailure() {
                categoriesView.showProgressbar(false);
                categoriesView.showMessage("Error..try again");
            }

            @Override
            public void onSuccess(List<CategoryData> categoryDatas) {
                categoriesView.showProgressbar(false);
                categoriesView.onDataReceived(categoryDatas);
            }


        });
    }
}