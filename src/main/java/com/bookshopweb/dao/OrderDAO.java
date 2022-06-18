package com.bookshopweb.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.bookshopweb.beans.Order;

@RegisterBeanMapper(Order.class)
public interface OrderDAO extends DAO<Order> {
    @Override
    @SqlUpdate("INSERT INTO orders VALUES (default, :userId, :status, :deliveryMethod, :deliveryPrice, :createdAt, :updatedAt)")
    @GetGeneratedKeys("id")
    long insert(@BindBean Order order);

    @Override
    @SqlUpdate("UPDATE orders SET userId = :userId, status = :status, deliveryMethod = :deliveryMethod, "
            + "deliveryPrice = :deliveryPrice, createdAt = :createdAt, updatedAt = :updatedAt WHERE id = :id")
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
    List<Order> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset, @Define("orderBy") String orderBy,
                               @Define("orderDir") String orderDir);

    @SqlQuery("select * from orders where userId = :userId order by orders.createdAt desc")
    List<Order> getOrdersByUserId(@Bind("userId") long userId);
}
