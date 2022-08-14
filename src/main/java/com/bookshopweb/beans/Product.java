package com.bookshopweb.beans;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Product {
    private long id;
    private String name;
    private double price;
    private double discount;
    private int quantity;
    private int totalBuy;
    private String author;
    private int pages;
    private String publisher;
    private int yearPublishing;
    @Nullable
    private String description;
    @Nullable
    private String imageName;
    private int shop;
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;
    @Nullable
    private LocalDateTime startsAt;
    @Nullable
    private LocalDateTime endsAt;

    public Product() {}

    public Product(long id,
                   String name,
                   double price,
                   double discount,
                   int quantity,
                   int totalBuy,
                   String author,
                   int pages,
                   String publisher,
                   int yearPublishing,
                   @Nullable String description,
                   @Nullable String imageName,
                   int shop,
                   LocalDateTime createdAt,
                   @Nullable LocalDateTime updatedAt,
                   @Nullable LocalDateTime startsAt,
                   @Nullable LocalDateTime endsAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.totalBuy = totalBuy;
        this.author = author;
        this.pages = pages;
        this.publisher = publisher;
        this.yearPublishing = yearPublishing;
        this.description = description;
        this.imageName = imageName;
        this.shop = shop;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(int totalBuy) {
        this.totalBuy = totalBuy;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getImageName() {
        return imageName;
    }

    public void setImageName(@Nullable String imageName) {
        this.imageName = imageName;
    }

    public int getShop() {
        return shop;
    }

    public void setShop(int shop) {
        this.shop = shop;
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
    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(@Nullable LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    @Nullable
    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(@Nullable LocalDateTime endsAt) {
        this.endsAt = endsAt;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("discount=" + discount)
                .add("quantity=" + quantity)
                .add("totalBuy=" + totalBuy)
                .add("author='" + author + "'")
                .add("pages=" + pages)
                .add("publisher='" + publisher + "'")
                .add("yearPublishing=" + yearPublishing)
                .add("description='" + description + "'")
                .add("imageName='" + imageName + "'")
                .add("shop=" + shop)
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("startsAt=" + startsAt)
                .add("endsAt=" + endsAt)
                .toString();
    }
}
