package com.bookshopweb.beans;

import org.jetbrains.annotations.Nullable;

import java.util.StringJoiner;

public class Category {
    private long id;
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String imageName;

    public Category() {}

    public Category(long id,
                    String name,
                    @Nullable String description,
                    @Nullable String imageName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageName = imageName;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Category.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("imageName='" + imageName + "'")
                .toString();
    }
}
