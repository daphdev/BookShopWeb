package com.bookshopweb.dao;

import com.bookshopweb.beans.WishlistItem;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(WishlistItem.class)
public interface WishlistItemDAO extends DAO<WishlistItem> {
    @Override
    @SqlUpdate("INSERT INTO wishlist_item VALUES (default, :userId, :productId, NOW())")
    @GetGeneratedKeys("id")
    long insert(@BindBean WishlistItem wishlistItem);

    @Override
    @SqlUpdate("UPDATE wishlist_item SET userId = :userId, productId = :productId, createdAt = :createdAt WHERE id = :id")
    void update(@BindBean WishlistItem wishlistItem);

    @Override
    @SqlUpdate("DELETE FROM wishlist_item WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM wishlist_item WHERE id = :id")
    Optional<WishlistItem> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM wishlist_item")
    List<WishlistItem> getAll();

    @Override
    @SqlQuery("SELECT * FROM wishlist_item LIMIT :limit OFFSET :offset")
    List<WishlistItem> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("SELECT * FROM wishlist_item ORDER BY <orderBy> <orderDir> LIMIT :limit OFFSET :offset")
    List<WishlistItem> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                      @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery("SELECT * FROM wishlist_item WHERE userId = :userId")
    List<WishlistItem> getByUserId(@Bind("userId") long userId);

    @SqlQuery("SELECT COUNT(id) FROM wishlist_item WHERE userId = :userId AND productId = :productId")
    int countByUserIdAndProductId(@Bind("userId") long userId, @Bind("productId") long productId);
}
