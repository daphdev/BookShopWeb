package com.bookshopweb.service;

import com.bookshopweb.beans.OrderItem;
import com.bookshopweb.dao.OrderItemDAO;

import java.util.List;

public class OrderItemService extends Service<OrderItem, OrderItemDAO> implements OrderItemDAO {
    public OrderItemService() {
        super(OrderItemDAO.class);
    }

    @Override
    public void bulkInsert(List<OrderItem> orderItems) {
        jdbi.useExtension(OrderItemDAO.class, dao -> dao.bulkInsert(orderItems));
    }
}
