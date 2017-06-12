package com.codenicely.brandstore.project.shops.model.data;

/**
 * Created by iket on 22/10/16.
 */
public class ShopData {
    private int shop_id;

    private String  name, image, address;
    private Float distance;

    public ShopData(int shop_id, String name, String image, String address, Float distance) {
        this.shop_id = shop_id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.distance = distance;
    }

    public int getShopId() {
        return shop_id;
    }

    public Float getDistance() {
        return distance;
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

}
