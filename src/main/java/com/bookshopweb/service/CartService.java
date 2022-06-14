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
}
