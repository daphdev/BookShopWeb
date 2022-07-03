package com.bookshopweb.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    long insert(T t);

    void update(T t);

    void delete(long id);

    Optional<T> getById(long id);

    List<T> getAll();

    List<T> getPart(int limit, int offset);

    List<T> getOrderedPart(int limit, int offset, String orderBy, String orderDir);
}
