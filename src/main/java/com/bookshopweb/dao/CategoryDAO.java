package com.bookshopweb.dao;

import com.bookshopweb.beans.Category;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Category.class)
public interface CategoryDAO extends DAO<Category> {
    @Override
    @SqlUpdate("INSERT INTO category VALUES (default, :name, :description, :imageName)")
    @GetGeneratedKeys("id")
    long insert(@BindBean Category category);

    @Override
    @SqlUpdate("UPDATE category SET name = :name, description = :description, imageName = :imageName WHERE id = :id")
    void update(@BindBean Category category);

    @Override
    @SqlUpdate("DELETE FROM category WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM category WHERE id = :id")
    Optional<Category> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM category")
    List<Category> getAll();

    @Override
    @SqlQuery("SELECT * FROM category LIMIT :limit OFFSET :offset")
    List<Category> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("SELECT * FROM category ORDER BY <orderBy> <orderDir> LIMIT :limit OFFSET :offset")
    List<Category> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                  @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery("SELECT c.* FROM product_category pc JOIN category c ON pc.categoryId = c.id WHERE productId = :productId")
    Optional<Category> getByProductId(@Bind("productId") long productId);

    @SqlQuery("SELECT COUNT(id) FROM category")
    int count();
}
