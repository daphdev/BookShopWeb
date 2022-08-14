package com.bookshopweb.dao;

import com.bookshopweb.beans.Order;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Order.class)
public interface OrderDAO extends DAO<Order> {
    @Override
    @SqlUpdate("INSERT INTO orders VALUES (default, :userId, :status, :deliveryMethod, :deliveryPrice, :createdAt, :updatedAt)")
    @GetGeneratedKeys("id")
    long insert(@BindBean Order order);

    @Override
    @SqlUpdate("UPDATE orders SET userId = :userId, status = :status, deliveryMethod = :deliveryMethod, " +
               "deliveryPrice = :deliveryPrice, createdAt = :createdAt, updatedAt = :updatedAt WHERE id = :id")
    void update(@BindBean Order order);

    @Override
    @SqlUpdate("DELETE FROM orders WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM orders WHERE id = :id")
    Optional<Order> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM orders")
    List<Order> getAll();

    @Override
    @SqlQuery("SELECT * FROM orders LIMIT :limit OFFSET :offset")
    List<Order> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("SELECT * FROM orders ORDER BY <orderBy> <orderDir> LIMIT :limit OFFSET :offset")
    List<Order> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                               @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery("SELECT * FROM orders WHERE userId = :userId ORDER BY orders.createdAt DESC LIMIT :limit OFFSET :offset ")
    List<Order> getOrderedPartByUserId(@Bind("userId") long userId, @Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("SELECT COUNT(orders.id) FROM orders WHERE userId = :userId")
    int countByUserId(@Bind("userId") long userId);

    @SqlUpdate("UPDATE orders SET status = 3, updatedAt = NOW() WHERE id = :id")
    void cancelOrder(@Bind("id") long id);

    @SqlQuery("SELECT COUNT(id) FROM orders")
    int count();

    @SqlUpdate("UPDATE orders SET status = 2, updatedAt = NOW() WHERE id = :id")
    void confirm(@Bind("id") long id);

    @SqlUpdate("UPDATE orders SET status = 3, updatedAt = NOW() WHERE id = :id")
    void cancel(@Bind("id") long id);

    @SqlUpdate("UPDATE orders SET status = 1, updatedAt = NOW() WHERE id = :id")
    void reset(@Bind("id") long id);
}
