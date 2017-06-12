package com.codenicely.brandstore.project.categories.view;

import com.codenicely.brandstore.project.categories.model.data.CategoryData;

import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public interface CategoriesView {
    void showMessage(String message);

    void showProgressbar(boolean show);

    void onDataReceived(List<CategoryData> categoryDatas);

    void onSelected(String category_id);
}
