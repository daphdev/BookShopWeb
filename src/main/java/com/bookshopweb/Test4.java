package com.bookshopweb;

import com.bookshopweb.beans.OrderItem;
import com.bookshopweb.service.OrderItemService;
import com.bookshopweb.utils.Protector;

import java.util.ArrayList;
import java.util.List;

public class Test4 {
    public static void main(String[] args) {
         final OrderItemService orderItemService = new OrderItemService();
         final OrderItem orderItem = new OrderItem();
        List<OrderItem> orderItems = Protector.of(() -> orderItemService.getByOrderId(5)).get(ArrayList::new);
        System.out.println(orderItems);
        double total = 0.0;
        for (OrderItem o : orderItems){
            total += o.getPrice() * o.getQuantity();
        }

        System.out.println(total);
    }
}
