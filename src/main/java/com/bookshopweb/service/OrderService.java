package com.bookshopweb.service;

import java.util.List;

import com.bookshopweb.beans.Order;
import com.bookshopweb.dao.OrderDAO;

public class OrderService extends Service<Order, OrderDAO> implements OrderDAO {
    public OrderService() {
        super(OrderDAO.class);
    }

	public List<Order> getOrdersByUserId(long userId) {
		return jdbi.withExtension(OrderDAO.class, dao -> dao.getOrdersByUserId(userId));
	}
}
