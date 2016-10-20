package com.example.aman.offercart_v1.home.view;

import com.example.aman.offercart_v1.home.model.data.CategoryData;

import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class CategoriesList {
    private List<CategoryData>categoryDatas;

    public CategoriesList(List<CategoryData> categoryDatas) {
        this.categoryDatas = categoryDatas;
    }

    public List<CategoryData> getCategoryDatas() {
        return categoryDatas;
    }
}
