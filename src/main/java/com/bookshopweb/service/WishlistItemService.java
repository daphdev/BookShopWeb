package com.bookshopweb.service;

import com.bookshopweb.beans.WishlistItem;
import com.bookshopweb.dao.WishlistItemDAO;

import java.util.List;

public class WishlistItemService extends Service<WishlistItem, WishlistItemDAO> implements WishlistItemDAO {
    public WishlistItemService() {
        super(WishlistItemDAO.class);
    }

    @Override
    public List<WishlistItem> getByUserId(long userId) {
        return jdbi.withExtension(WishlistItemDAO.class, dao -> dao.getByUserId(userId));
    }

    @Override
    public int countByUserIdAndProductId(long userId, long productId) {
        return jdbi.withExtension(WishlistItemDAO.class, dao -> dao.countByUserIdAndProductId(userId, productId));
    }
}
