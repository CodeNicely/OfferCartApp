package com.codenicely.brandstore.project.my_offers.model.data;

import java.util.List;

/**
 * Created by iket on 3/11/16.
 */
public class OrdersData {
    private List<OrderDetails> my_offer_list;
    private String message;
    private boolean success;

    public OrdersData(List<OrderDetails> my_offer_list, String message, boolean success) {
        this.my_offer_list = my_offer_list;
        this.message = message;
        this.success = success;
    }

    public List<OrderDetails> getMy_offer_list() {
        return my_offer_list;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
