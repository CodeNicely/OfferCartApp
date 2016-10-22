package com.example.aman.offercart_v1.shops.model.data;

/**
 * Created by iket on 22/10/16.
 */
public class ShopData {
    private String id,name,image,address,phone;

    public ShopData(String id, String name, String image, String address, String phone) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
