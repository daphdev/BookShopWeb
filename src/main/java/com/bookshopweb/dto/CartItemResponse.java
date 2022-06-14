package com.bookshopweb.dto;

import java.util.StringJoiner;

public class CartItemResponse {
    private final long id;
    private final long cartId;
    private final long productId;
    private final String productName;
    private final double productPrice;
    private final double productDiscount;
    private final int productQuantity;
    private final String productImageName;
    private final int quantity;

    public CartItemResponse(long id,
                            long cartId,
                            long productId,
                            String productName,
                            double productPrice,
                            double productDiscount,
                            int productQuantity,
                            String productImageName,
                            int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productQuantity = productQuantity;
        this.productImageName = productImageName;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public long getCartId() {
        return cartId;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getProductDiscount() {
        return productDiscount;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductImageName() {
        return productImageName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CartItemResponse.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("cartId=" + cartId)
                .add("productId=" + productId)
                .add("productName='" + productName + "'")
                .add("productPrice=" + productPrice)
                .add("productDiscount=" + productDiscount)
                .add("productQuantity=" + productQuantity)
                .add("productImageName='" + productImageName + "'")
                .add("quantity=" + quantity)
                .toString();
    }
}
