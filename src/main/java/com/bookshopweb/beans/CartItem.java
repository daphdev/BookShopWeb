package com.bookshopweb.beans;

import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class CartItem {
    private long id;
    private long cartId;
    private long productId;
    private int quantity;
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;
    @Nullable
    private Product product;

    public CartItem() {}

    public CartItem(long id,
                    long cartId,
                    long productId,
                    int quantity,
                    LocalDateTime createdAt,
                    @Nullable LocalDateTime updatedAt) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@Nullable LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Nullable
    @Nested("product")
    public Product getProduct() {
        return product;
    }

    public void setProduct(@Nullable Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CartItem.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("cartId=" + cartId)
                .add("productId=" + productId)
                .add("quantity=" + quantity)
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("product=" + product)
                .toString();
    }
}
