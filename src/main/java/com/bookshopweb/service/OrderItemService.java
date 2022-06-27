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

    @Override
    public List<OrderItem> getByOrderId(List<Long> ids, int limit, int offset) {
        return jdbi.withExtension(OrderItemDAO.class, dao -> dao.getByOrderId(ids, limit, offset));
    }

    @Override
    public int countByOrderId(List<Long> ids) {
        return jdbi.withExtension(OrderItemDAO.class, dao -> dao.countByOrderId(ids));
    }

    @Override
    public List<String> getProductNamesByOrderId(int orderId) {
        return jdbi.withExtension(OrderItemDAO.class, dao->dao.getProductNamesByOrderId(orderId));
    }


}
