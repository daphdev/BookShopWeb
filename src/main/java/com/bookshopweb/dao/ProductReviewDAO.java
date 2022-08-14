package com.bookshopweb.dao;

import com.bookshopweb.beans.ProductReview;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(ProductReview.class)
public interface ProductReviewDAO extends DAO<ProductReview> {
    @Override
    @SqlUpdate("INSERT INTO product_review VALUES (default, :userId, :productId, :ratingScore, :content, :isShow, " +
               ":createdAt, :updatedAt)")
    @GetGeneratedKeys("id")
    long insert(@BindBean ProductReview productReview);

    @Override
    @SqlUpdate("UPDATE product_review SET ratingScore = :ratingScore, content = :content, updatedAt = NOW() WHERE id = :id")
    void update(@BindBean ProductReview productReview);

    @Override
    @SqlUpdate("DELETE FROM product_review WHERE id = :id")
    void delete(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM product_review WHERE id = :id")
    Optional<ProductReview> getById(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT * FROM product_review")
    List<ProductReview> getAll();

    @Override
    @SqlQuery("SELECT * FROM product_review LIMIT :limit OFFSET :offset")
    List<ProductReview> getPart(@Bind("limit") int limit, @Bind("offset") int offset);

    @Override
    @SqlQuery("SELECT * FROM product_review ORDER BY <orderBy> <orderDir> LIMIT :limit OFFSET :offset")
    List<ProductReview> getOrderedPart(@Bind("limit") int limit, @Bind("offset") int offset,
                                       @Define("orderBy") String orderBy, @Define("orderDir") String orderDir);

    @SqlQuery("SELECT pr.*, u.fullname user_fullname " +
              "FROM product_review pr " +
              "JOIN user u ON pr.userId = u.id " +
              "WHERE productId = :productId " +
              "ORDER BY <orderBy> <orderDir> " +
              "LIMIT :limit OFFSET :offset")
    List<ProductReview> getOrderedPartByProductId(@Bind("limit") int limit, @Bind("offset") int offset,
                                                  @Define("orderBy") String orderBy, @Define("orderDir") String orderDir,
                                                  @Bind("productId") long productId);

    @SqlQuery("SELECT COUNT(id) FROM product_review WHERE productId = :productId")
    int countByProductId(@Bind("productId") long productId);

    @SqlQuery("SELECT SUM(ratingScore) FROM product_review WHERE productId = :productId")
    int sumRatingScoresByProductId(@Bind("productId") long productId);

    @SqlQuery("SELECT COUNT(id) FROM product_review")
    int count();

    @SqlUpdate("UPDATE product_review SET isShow = 0, updatedAt = NOW() WHERE id = :id")
    void hide(@Bind("id") long id);

    @SqlUpdate("UPDATE product_review SET isShow = 1, updatedAt = NOW() WHERE id = :id")
    void show(@Bind("id") long id);
}
