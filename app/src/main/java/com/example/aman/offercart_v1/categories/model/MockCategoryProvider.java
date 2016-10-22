package com.example.aman.offercart_v1.categories.model;

import com.example.aman.offercart_v1.categories.model.data.CategoriesList;
import com.example.aman.offercart_v1.categories.model.data.CategoryData;
import com.example.aman.offercart_v1.categories.view.OnCategoriesReceived;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public class MockCategoryProvider implements CategoriesProvider {

    @Override
    public void getCategories(OnCategoriesReceived onCategoriesReceived) {
        List<CategoryData> categoryDatas=new ArrayList<>();

        CategoryData clothes=new CategoryData("Clothes","http://image.flaticon.com/icons/png/512/194/194116.png","1");
        categoryDatas.add(clothes);

        clothes=new CategoryData("Food","https://cdn2.iconfinder.com/data/icons/food-solid-icons-vol-1/48/001-512.png","2");
        categoryDatas.add(clothes);

        clothes=new CategoryData("Stationary","https://cdn2.iconfinder.com/data/icons/food-solid-icons-vol-1/48/001-512.png","2");
        categoryDatas.add(clothes);

        clothes=new CategoryData("Electronics","https://cdn2.iconfinder.com/data/icons/food-solid-icons-vol-1/48/001-512.png","2");
        categoryDatas.add(clothes);

        clothes=new CategoryData("Gifts","https://cdn2.iconfinder.com/data/icons/food-solid-icons-vol-1/48/001-512.png","2");
        categoryDatas.add(clothes);

        CategoriesList categoriesList=new CategoriesList(categoryDatas,"Success",true);
        onCategoriesReceived.onSuccess(categoriesList);

        onCategoriesReceived.onFailure();
    }
}
