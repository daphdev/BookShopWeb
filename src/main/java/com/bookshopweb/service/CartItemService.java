package com.bookshopweb.service;

import com.bookshopweb.beans.CartItem;
import com.bookshopweb.dao.CartItemDAO;

import java.util.List;
import java.util.Optional;

public class CartItemService extends Service<CartItem, CartItemDAO> implements CartItemDAO {
    public CartItemService() {
        super(CartItemDAO.class);
    }

    @Override
    public List<CartItem> getByCartId(long cartId) {
        return jdbi.withExtension(CartItemDAO.class, dao -> dao.getByCartId(cartId));
    }

    @Override
    public Optional<CartItem> getByCartIdAndProductId(long cartId, long productId) {
        return jdbi.withExtension(CartItemDAO.class, dao -> dao.getByCartIdAndProductId(cartId, productId));
    }

    @Override
    public int sumQuantityByUserId(long userId) {
        return jdbi.withExtension(CartItemDAO.class, dao -> dao.sumQuantityByUserId(userId));
    }
}
