package com.codenicely.brandstore.project.categories.model;

import com.codenicely.brandstore.project.categories.model.data.CategoriesList;
import com.codenicely.brandstore.project.categories.model.data.CategoryData;
import com.codenicely.brandstore.project.categories.view.OnCategoriesReceived;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public class MockCategoryProvider implements CategoriesProvider {

    @Override
    public void getCategories(String access_token, OnCategoriesReceived onCategoriesReceived) {
        List<CategoryData> categoryDatas = new ArrayList<>();

        CategoryData clothes = new CategoryData("Clothes", "http://image.flaticon.com/icons/png/512/194/194116.png", 1,"");
        categoryDatas.add(clothes);


        CategoriesList categoriesList = new CategoriesList(categoryDatas, "Success", true);
        onCategoriesReceived.onSuccess(categoriesList);

        onCategoriesReceived.onFailure();
    }
}
