package com.bookshopweb.beans;

import org.jdbi.v3.core.mapper.Nested;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class ProductReview {
    private long id;
    private long userId;
    private long productId;
    private int ratingScore;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;

    public ProductReview() {}

    public ProductReview(long id,
                         long userId,
                         long productId,
                         int ratingScore,
                         String content,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt,
                         User user) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.ratingScore = ratingScore;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Nested("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProductReview.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId=" + userId)
                .add("productId=" + productId)
                .add("ratingScore=" + ratingScore)
                .add("content='" + content + "'")
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("user=" + user)
                .toString();
    }
}
