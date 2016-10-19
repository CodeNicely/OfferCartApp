package com.example.aman.offercart_v1.shopcategories.models.data;

/**
 * Created by aman on 19/10/16.
 */

public class subcategory_data {


    private String subcategory_id;
    private String subcategory_name;
    private int data_type;

    public subcategory_data(String subcategory_id, String subcategory_name, int data_type)
    {

        this.subcategory_id = subcategory_id;
        this.subcategory_name = subcategory_name;
        this.data_type = data_type;
    }

    public String getSubcategory_id()
    {
        return subcategory_id;
    }

    public String getSubcategory_name()
    {
        return subcategory_name;
    }

    public int getData_type()
    {
        return data_type;
    }

    public void setSubcategory_id(String subcategory_id)
    {
        this.subcategory_id = subcategory_id;
    }

    public void setSubcategory_name(String subcategory_name)
    {
        this.subcategory_name = subcategory_name;
    }
}
