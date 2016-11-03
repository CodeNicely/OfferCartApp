package com.codenicely.discountstore.project.shops.model.data;

/**
 * Created by iket on 22/10/16.
 */
public class ShopData {
    private String shop_id, name, image, address;

    public ShopData(String shop_id, String name, String image, String address, String phone) {
        this.shop_id = shop_id;
        this.name = name;
        this.image = image;
        this.address = address;
    }

    public String getShopId() {
        return shop_id;
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
