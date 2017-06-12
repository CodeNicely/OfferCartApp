package com.codenicely.brandstore.project.shop_admin.shop_register.data;

/**
 * Created by meghal on 14/4/17.
 */

public class CategoryData {

    private int id;
    private String name;

    public CategoryData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
