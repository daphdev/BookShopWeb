package com.bookshopweb.dao;

import com.bookshopweb.beans.CartItem;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(CartItem.class)
public interface CartItemDAO extends DAO<CartItem> {
    @Override
    @SqlUpdate("INSERT INTO cart_item VALUES (default, :cartId, :productId, :quantity, :createdAt, :updatedAt)")
    @GetGeneratedKeys("id")
    long insert(@BindBean CartItem cartItem);

    @Override
    @SqlUpdate("UPDATE cart_item SET cartId = :cartId, productId = :productId, quantity = :quantity, " +
               "createdAt = :createdAt, updatedAt = :updatedAt WHERE id = :id")
    void update(@BindBean CartItem cartItem);

    @Override
    @SqlUpdate("DELETE FROM cart_item WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM cart_item WHERE id = :id")
    Optional<CartItem> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM cart_item")
    List<CartItem> getAll();

    @Override
    @SqlQuery("SELECT * FROM cart_item LIMIT :limit OFFSET :offset")
    List<CartItem> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("SELECT * FROM cart_item ORDER BY <orderBy> <orderDir> LIMIT :limit OFFSET :offset")
    List<CartItem> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                  @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery("SELECT ci.*, p.name product_name, p.price product_price, p.discount product_discount, " +
              "p.quantity product_quantity, p.imageName product_imageName " +
              "FROM cart_item ci " +
              "JOIN product p on p.id = ci.productId " +
              "WHERE cartId = :cartId " +
              "ORDER BY createdAt DESC")
    List<CartItem> getByCartId(@Bind("cartId") long cartId);

    @SqlQuery("SELECT * FROM cart_item WHERE cartId = :cartId AND productId = :productId")
    Optional<CartItem> getByCartIdAndProductId(@Bind("cartId") long cartId, @Bind("productId") long productId);

    @SqlQuery("SELECT SUM(ci.quantity) FROM cart_item ci JOIN cart c on c.id = ci.cartId WHERE c.userId = :userId;")
    int sumQuantityByUserId(@Bind("userId") long userId);
}
