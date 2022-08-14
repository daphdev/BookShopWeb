package com.bookshopweb.dao;

import com.bookshopweb.beans.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Product.class)
public interface ProductDAO extends DAO<Product> {
    @Override
    @SqlUpdate("INSERT INTO product VALUES (default, :name, :price, :discount, :quantity, " +
               ":totalBuy, :author, :pages, :publisher, :yearPublishing, :description, " +
               ":imageName, :shop, :createdAt, :updatedAt, :startsAt, :endsAt)")
    @GetGeneratedKeys("id")
    long insert(@BindBean Product product);

    @Override
    @SqlUpdate("UPDATE product SET name = :name, price = :price, discount = :discount, quantity = :quantity, " +
               "totalBuy = :totalBuy, author = :author, pages = :pages, publisher = :publisher, " +
               "yearPublishing = :yearPublishing, description = :description, imageName = :imageName, " +
               "shop = :shop, updatedAt = :updatedAt, startsAt = :startsAt, endsAt = :endsAt WHERE id = :id")
    void update(@BindBean Product product);

    @Override
    @SqlUpdate("DELETE FROM product WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM product WHERE id = :id")
    Optional<Product> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM product")
    List<Product> getAll();

    @Override
    @SqlQuery("SELECT * FROM product LIMIT :limit OFFSET :offset")
    List<Product> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("SELECT * FROM product ORDER BY <orderBy> <orderDir> LIMIT :limit OFFSET :offset")
    List<Product> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                 @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery("SELECT p.* " +
              "FROM product_category pc " +
              "JOIN product p ON pc.productId = p.id " +
              "WHERE pc.categoryId = :categoryId " +
              "ORDER BY p.<orderBy> <orderDir> " +
              "LIMIT :limit OFFSET :offset")
    List<Product> getOrderedPartByCategoryId(@Bind("limit") int limit, @Bind("offset") int offset,
                                             @Define("orderBy") String orderBy, @Define("orderDir") String orderDir,
                                             @Bind("categoryId") long categoryId);

    @SqlQuery("SELECT COUNT(productId) FROM product_category WHERE categoryId = :categoryId")
    int countByCategoryId(@Bind("categoryId") long categoryId);

    @SqlQuery("SELECT p.* FROM product_category pc " +
              "JOIN product p ON pc.productId = p.id " +
              "WHERE pc.categoryId = :categoryId " +
              "ORDER BY RAND() " +
              "LIMIT :limit OFFSET :offset")
    List<Product> getRandomPartByCategoryId(@Bind("limit") int limit, @Bind("offset") int offset,
                                            @Bind("categoryId") long categoryId);

    @SqlQuery("SELECT DISTINCT p.publisher " +
              "FROM product_category pc " +
              "JOIN product p ON pc.productId = p.id " +
              "WHERE pc.categoryId = :categoryId " +
              "ORDER BY p.publisher")
    List<String> getPublishersByCategoryId(@Bind("categoryId") long categoryId);

    @SqlQuery("SELECT COUNT(p.id) " +
              "FROM product_category pc " +
              "JOIN product p ON pc.productId = p.id " +
              "WHERE pc.categoryId = :categoryId " +
              "AND <filters>")
    int countByCategoryIdAndFilters(@Bind("categoryId") long categoryId, @Define("filters") String filters);

    @SqlQuery("SELECT p.* " +
              "FROM product_category pc " +
              "JOIN product p ON pc.productId = p.id " +
              "WHERE pc.categoryId = :categoryId " +
              "AND <filters> " +
              "ORDER BY p.<orderBy> <orderDir> " +
              "LIMIT :limit OFFSET :offset")
    List<Product> getOrderedPartByCategoryIdAndFilters(@Bind("limit") int limit, @Bind("offset") int offset,
                                                       @Define("orderBy") String orderBy, @Define("orderDir") String orderDir,
                                                       @Bind("categoryId") long categoryId, @Define("filters") String filters);

    @SqlQuery("SELECT COUNT(id) FROM product")
    int count();

    @SqlUpdate("INSERT product_category VALUES (:productId, :categoryId)")
    void insertProductCategory(@Bind("productId") long productId, @Bind("categoryId") long categoryId);

    @SqlUpdate("UPDATE product_category SET categoryId = :categoryId WHERE productId = :productId")
    void updateProductCategory(@Bind("productId") long productId, @Bind("categoryId") long categoryId);

    @SqlUpdate("DELETE FROM product_category WHERE productId = :productId AND categoryId = :categoryId")
    void deleteProductCategory(@Bind("productId") long productId, @Bind("categoryId") long categoryId);

    @SqlQuery("SELECT * FROM product WHERE name LIKE CONCAT('%', :query, '%') LIMIT :limit OFFSET :offset")
    List<Product> getByQuery(@Bind("query") String query, @Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("SELECT COUNT(id) FROM product WHERE name LIKE CONCAT('%', :query, '%')")
    int countByQuery(@Bind("query") String query);
}
