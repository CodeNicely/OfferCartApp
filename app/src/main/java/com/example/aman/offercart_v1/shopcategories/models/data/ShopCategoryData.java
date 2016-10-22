package com.example.aman.offercart_v1.shopcategories.models.data;

import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public class ShopCategoryData {

    private List<ShopCategoryDetails> subcategory_data;
    private boolean success;


    public ShopCategoryData(List<ShopCategoryDetails> ShopCategoryDetails, boolean success)
    {
        this.subcategory_data = ShopCategoryDetails;
        this.success = success;
    }

    public List<ShopCategoryDetails> getSubcategory_data()
    {
        return subcategory_data;
    }

    public boolean isSuccess()
    {
        return success;
    }
}

