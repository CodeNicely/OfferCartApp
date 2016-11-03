package com.codenicely.discountstore.project.categories.model.data;

/**
 * Created by iket on 19/10/16.
 */
public class CategoryData {
    private String name, image, id;
    private String description;

    public CategoryData(String name, String image, String id, String description) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
