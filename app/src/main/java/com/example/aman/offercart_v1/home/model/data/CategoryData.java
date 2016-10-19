package com.example.aman.offercart_v1.home.model.data;

/**
 * Created by iket on 19/10/16.
 */
public class CategoryData {
    private String Category_name,image_url;

    public CategoryData(String category_name, String image_url) {
        Category_name = category_name;
        this.image_url = image_url;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public String getImage_url() {
        return image_url;
    }
}
