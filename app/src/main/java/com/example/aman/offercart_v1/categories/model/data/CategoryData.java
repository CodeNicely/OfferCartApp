package com.example.aman.offercart_v1.categories.model.data;

/**
 * Created by iket on 19/10/16.
 */
public class CategoryData {
    private String name,image,id;

    public CategoryData(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }
}
