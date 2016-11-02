package com.example.aman.offercart_v1.categories.presenter;

import com.example.aman.offercart_v1.categories.model.CategoriesProvider;
import com.example.aman.offercart_v1.categories.model.data.CategoriesList;
import com.example.aman.offercart_v1.categories.view.CategoriesView;
import com.example.aman.offercart_v1.categories.view.OnCategoriesReceived;

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
    public void getCategories(String access_token) {

        categoriesView.showProgressbar(true);

        categoriesProvider.getCategories(access_token,new OnCategoriesReceived() {
            @Override
            public void onFailure() {
                categoriesView.showProgressbar(false);
                categoriesView.showMessage("Error..try again");
            }

            @Override
            public void onSuccess(CategoriesList categoriesList) {
                if(categoriesList.isSuccess())
                {
                    categoriesView.showProgressbar(false);
                    categoriesView.onDataReceived(categoriesList.getCategoryDatas());

                }
                else
                {
                    categoriesView.showProgressbar(false);
                    categoriesView.onDataReceived(categoriesList.getCategoryDatas());

                }
                }

        });
    }
}
