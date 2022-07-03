package com.bookshopweb.service;

import com.bookshopweb.beans.Cart;
import com.bookshopweb.dao.CartDAO;

import java.util.Optional;

public class CartService extends Service<Cart, CartDAO> implements CartDAO {
    public CartService() {
        super(CartDAO.class);
    }

    @Override
    public Optional<Cart> getByUserId(long userId) {
        return jdbi.withExtension(CartDAO.class, dao -> dao.getByUserId(userId));
    }

    @Override
    public int countCartItemQuantityByUserId(long userId) {
        return jdbi.withExtension(CartDAO.class, dao -> dao.countCartItemQuantityByUserId(userId));
    }

    @Override
    public int countOrderByUserId(long userId) {
        return jdbi.withExtension(CartDAO.class, dao -> dao.countOrderByUserId(userId));
    }

    @Override
    public int countOrderDeliverByUserId(long userId) {
        return jdbi.withExtension(CartDAO.class, dao -> dao.countOrderDeliverByUserId(userId));
    }

    @Override
    public int countOrderReceivedByUserId(long userId) {
        return jdbi.withExtension(CartDAO.class, dao -> dao.countOrderReceivedByUserId(userId));
    }
}
