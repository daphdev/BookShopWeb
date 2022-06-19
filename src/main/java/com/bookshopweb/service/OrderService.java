package com.bookshopweb.service;

import com.bookshopweb.beans.Order;
import com.bookshopweb.dao.OrderDAO;

import java.util.List;

public class OrderService extends Service<Order, OrderDAO> implements OrderDAO {
    public OrderService() {
        super(OrderDAO.class);
    }

    public List<Order> getOrdersByUserId(long userId) {
        return jdbi.withExtension(OrderDAO.class, dao -> dao.getOrdersByUserId(userId));
    }
}
