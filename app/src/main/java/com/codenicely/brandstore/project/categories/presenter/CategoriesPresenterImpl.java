package com.codenicely.brandstore.project.categories.presenter;

import com.codenicely.brandstore.project.categories.model.CategoriesProvider;
import com.codenicely.brandstore.project.categories.model.data.CategoriesList;
import com.codenicely.brandstore.project.categories.view.CategoriesView;
import com.codenicely.brandstore.project.categories.view.OnCategoriesReceived;

/**
 * Created by iket on 19/10/16.
 */
public class CategoriesPresenterImpl implements CategoriesPresenter {
    private CategoriesView categoriesView;
    private CategoriesProvider categoriesProvider;

    public CategoriesPresenterImpl(CategoriesView categoriesView, CategoriesProvider categoriesProvider) {
        this.categoriesView = categoriesView;
        this.categoriesProvider = categoriesProvider;
    }

    @Override
    public void getCategories(String access_token) {

        categoriesView.showProgressbar(true);

        categoriesProvider.getCategories(access_token, new OnCategoriesReceived() {
            @Override
            public void onFailure() {
                categoriesView.showProgressbar(false);
                categoriesView.showMessage("Error..try again");
            }

            @Override
            public void onSuccess(CategoriesList categoriesList) {
                if (categoriesList.isSuccess()) {
                    categoriesView.showProgressbar(false);
                    categoriesView.onDataReceived(categoriesList.getCategoryDatas());

                } else {
                    categoriesView.showProgressbar(false);
                    categoriesView.onDataReceived(categoriesList.getCategoryDatas());

                }
            }

        });
    }
}
