package com.codenicely.brandstore.project.categories.model.data;

import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class CategoriesList {
    private List<CategoryData> categoryDatas;
    private String message;
    private boolean success;

    public CategoriesList(List<CategoryData> categoryDatas, String message, boolean success) {
        this.categoryDatas = categoryDatas;
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<CategoryData> getCategoryDatas() {
        return categoryDatas;
    }
}
