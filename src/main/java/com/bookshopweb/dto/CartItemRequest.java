package com.bookshopweb.dto;

import java.util.StringJoiner;

public class CartItemRequest {
    private final long userId;
    private final long productId;
    private final int quantity;

    public CartItemRequest(long userId,
                           long productId,
                           int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getUserId() {
        return userId;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CartItemRequest.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("productId=" + productId)
                .add("quantity=" + quantity)
                .toString();
    }
}
