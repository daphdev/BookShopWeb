package com.bookshopweb.dto;

import java.util.List;
import java.util.StringJoiner;

public class OrderRequest {
    private final long cartId;
    private final long userId;
    private final int deliveryMethod;
    private final double deliveryPrice;
    private final List<OrderItemRequest> orderItems;

    public OrderRequest(long cartId,
                        long userId,
                        int deliveryMethod,
                        double deliveryPrice,
                        List<OrderItemRequest> orderItems) {
        this.cartId = cartId;
        this.userId = userId;
        this.deliveryMethod = deliveryMethod;
        this.deliveryPrice = deliveryPrice;
        this.orderItems = orderItems;
    }

    public long getCartId() {
        return cartId;
    }

    public long getUserId() {
        return userId;
    }

    public int getDeliveryMethod() {
        return deliveryMethod;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderRequest.class.getSimpleName() + "[", "]")
                .add("cartId=" + cartId)
                .add("userId=" + userId)
                .add("deliveryMethod=" + deliveryMethod)
                .add("deliveryPrice=" + deliveryPrice)
                .add("orderItems=" + orderItems)
                .toString();
    }
}
