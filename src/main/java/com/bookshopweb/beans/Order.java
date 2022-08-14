package com.bookshopweb.beans;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

public class Order {
    private long id;
    private long userId;
    private int status;
    private int deliveryMethod;
    private double deliveryPrice;
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;
    @Nullable
    private User user;
    @Nullable
    private List<OrderItem> orderItems;
    private double totalPrice;

    public Order() {}

    public Order(long id,
                 long userId,
                 int status,
                 int deliveryMethod,
                 double deliveryPrice,
                 LocalDateTime createdAt,
                 @Nullable LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.deliveryMethod = deliveryMethod;
        this.deliveryPrice = deliveryPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(int deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
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
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Nullable
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(@Nullable List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId=" + userId)
                .add("status=" + status)
                .add("deliveryMethod=" + deliveryMethod)
                .add("deliveryPrice=" + deliveryPrice)
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("user=" + user)
                .add("orderItems=" + orderItems)
                .add("totalPrice=" + totalPrice)
                .toString();
    }
}
