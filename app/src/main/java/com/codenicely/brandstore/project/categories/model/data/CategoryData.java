package com.codenicely.brandstore.project.categories.model.data;

/**
 * Created by iket on 19/10/16.
 */
public class CategoryData {
    private String name, image;
    private int category_id;
    private String description;

    public CategoryData(String name, String image, int category_id, String description) {
        this.name = name;
        this.image = image;
        this.category_id = category_id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return category_id;
    }

    public String getDescription() {
        return description;
    }
}
