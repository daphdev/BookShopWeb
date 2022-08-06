package com.bookshopweb.beans;

import java.time.LocalDateTime;

public class WishlistItem {
    private long id;
    private long userId;
    private long productId;
    private LocalDateTime createAt;

    public WishlistItem() {
    }

    public WishlistItem(long id, long userId, long productId, LocalDateTime createAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.createAt = createAt;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "WishlistItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", createAt=" + createAt +
                '}';
    }
}
