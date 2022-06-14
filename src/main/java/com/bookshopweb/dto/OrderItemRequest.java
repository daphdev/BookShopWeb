package com.bookshopweb.dto;

import java.util.StringJoiner;

public class OrderItemRequest {
    private final long productId;
    private final double price;
    private final double discount;
    private final int quantity;

    public OrderItemRequest(long productId,
                            double price,
                            double discount,
                            int quantity) {
        this.productId = productId;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderItemRequest.class.getSimpleName() + "[", "]")
                .add("productId=" + productId)
                .add("price=" + price)
                .add("discount=" + discount)
                .add("quantity=" + quantity)
                .toString();
    }
}
