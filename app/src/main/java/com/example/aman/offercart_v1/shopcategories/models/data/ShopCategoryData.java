package com.example.aman.offercart_v1.shopcategories.models.data;

import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public class ShopCategoryData {

    private List<subcategory_data> subcategory_data;
    private boolean success;


    public ShopCategoryData(List<subcategory_data> subcategory_data, boolean success)
    {
        this.subcategory_data = subcategory_data;
        this.success = success;
    }

    public List<subcategory_data> getSubcategory_data()
    {
        return subcategory_data;
    }

    public boolean isSuccess()
    {
        return success;
    }
}

