package com.example.aman.offercart_v1.my_orders.model.data;

import java.util.List;

/**
 * Created by iket on 3/11/16.
 */
public class OrdersData {
    private List<OrderDetails> orders;
    private String message;
    private boolean success;

    public OrdersData(List<OrderDetails> orders, String message, boolean success) {
        this.orders = orders;
        this.message = message;
        this.success = success;
    }

    public List<OrderDetails> getOrders() {
        return orders;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
