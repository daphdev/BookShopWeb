package com.bookshopweb.dao;

import com.bookshopweb.beans.OrderItem;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(OrderItem.class)
public interface OrderItemDAO extends DAO<OrderItem> {
    @Override
    @SqlUpdate("INSERT INTO order_item VALUES (default, :orderId, :productId, :price, :discount, :quantity, :createdAt, :updatedAt)")
    @GetGeneratedKeys("id")
    long insert(@BindBean OrderItem orderItem);

    @Override
    @SqlUpdate("UPDATE order_item SET orderId = :orderId, productId = :productId, price = :price, discount = :discount, " +
               "quantity = :quantity, createdAt = :createdAt, updatedAt = :updatedAt WHERE id = :id")
    void update(@BindBean OrderItem orderItem);

    @Override
    @SqlUpdate("DELETE FROM order_item WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM order_item WHERE id = :id")
    Optional<OrderItem> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM order_item")
    List<OrderItem> getAll();

    @Override
    @SqlQuery("SELECT * FROM order_item LIMIT :limit OFFSET :offset")
    List<OrderItem> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("SELECT * FROM order_item ORDER BY <orderBy> <orderDir> LIMIT :limit OFFSET :offset")
    List<OrderItem> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                   @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlBatch("INSERT INTO order_item VALUES (default, :orderId, :productId, :price, :discount, :quantity, :createdAt, :updatedAt)")
    void bulkInsert(@BindBean List<OrderItem> orderItems);

    @SqlQuery("SELECT name FROM product p JOIN order_item o ON p.id = o.productId WHERE o.orderId = :orderId")
    List<String> getProductNamesByOrderId(@Bind("orderId") long orderId);

    @SqlQuery("SELECT * FROM order_item WHERE orderId = :orderId")
    List<OrderItem> getByOrderId(@Bind("orderId") long orderId);
}
