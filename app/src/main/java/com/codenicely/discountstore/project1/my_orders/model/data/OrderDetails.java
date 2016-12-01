package com.codenicely.discountstore.project1.my_orders.model.data;

/**
 * Created by iket on 3/11/16.
 */
public class OrderDetails {
    private String title,address,shop,date,cost;

    public OrderDetails(String title, String address, String shop, String date, String cost) {
        this.title = title;
        this.address = address;
        this.shop = shop;
        this.date = date;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getShop() {
        return shop;
    }

    public String getDate() {
        return date;
    }

    public String getCost() {
        return cost;
    }
}
