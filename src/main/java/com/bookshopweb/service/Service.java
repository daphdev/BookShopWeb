package com.bookshopweb.service;

import com.bookshopweb.dao.DAO;
import com.bookshopweb.utils.JdbiUtils;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;

public abstract class Service<T, D extends DAO<T>> implements DAO<T> {
    private final Class<D> daoClass;
    protected final Jdbi jdbi = JdbiUtils.createInstance();

    public Service(Class<D> daoClass) {
        this.daoClass = daoClass;
    }

    @Override
    public long insert(T t) {
        return jdbi.withExtension(daoClass, dao -> dao.insert(t));
    }

    @Override
    public void update(T t) {
        jdbi.useExtension(daoClass, dao -> dao.update(t));
    }

    @Override
    public void delete(long id) {
        jdbi.useExtension(daoClass, dao -> dao.delete(id));
    }

    @Override
    public Optional<T> getById(long id) {
        return jdbi.withExtension(daoClass, dao -> dao.getById(id));
    }

    @Override
    public List<T> getAll() {
        return jdbi.withExtension(daoClass, DAO::getAll);
    }

    @Override
    public List<T> getPart(int limit, int offset) {
        return jdbi.withExtension(daoClass, dao -> dao.getPart(limit, offset));
    }

    @Override
    public List<T> getOrderedPart(int limit, int offset, String orderBy, String orderDir) {
        return jdbi.withExtension(daoClass, dao -> dao.getOrderedPart(limit, offset, orderBy, orderDir));
    }
}
