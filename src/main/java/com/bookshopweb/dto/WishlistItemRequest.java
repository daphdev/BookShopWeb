package com.bookshopweb.dto;

import java.util.StringJoiner;

public class WishlistItemRequest {
    private final long userId;
    private final long productId;

    public WishlistItemRequest(long userId, long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WishlistItemRequest.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("productId=" + productId)
                .toString();
    }
}
