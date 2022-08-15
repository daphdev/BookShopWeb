package com.bookshopweb.beans;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class WishlistItem {
    private long id;
    private long userId;
    private long productId;
    private LocalDateTime createdAt;
    @Nullable
    private Product product;

    public WishlistItem() {}

    public WishlistItem(long id,
                        long userId,
                        long productId,
                        LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public Product getProduct() {
        return product;
    }

    public void setProduct(@Nullable Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WishlistItem.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId=" + userId)
                .add("productId=" + productId)
                .add("createdAt=" + createdAt)
                .add("product=" + product)
                .toString();
    }
}
