package com.bookshopweb.service;

import com.bookshopweb.beans.Product;
import com.bookshopweb.dao.ProductDAO;
import com.bookshopweb.utils.Protector;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService extends Service<Product, ProductDAO> implements ProductDAO {
    public ProductService() {
        super(ProductDAO.class);
    }

    @Override
    public List<Product> getOrderedPartByCategoryId(int limit, int offset, String orderBy, String orderDir, long categoryId) {
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getOrderedPartByCategoryId(limit, offset, orderBy, orderDir, categoryId));
    }

    @Override
    public int countByCategoryId(long categoryId) {
        return jdbi.withExtension(ProductDAO.class, dao -> dao.countByCategoryId(categoryId));
    }

    @Override
    public List<Product> getRandomPartByCategoryId(int limit, int offset, long categoryId) {
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getRandomPartByCategoryId(limit, offset, categoryId));
    }

    @Override
    public List<String> getPublishersByCategoryId(long categoryId) {
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getPublishersByCategoryId(categoryId));
    }

    @Override
    public int countByCategoryIdAndFilters(long categoryId, String filters) {
        return jdbi.withExtension(ProductDAO.class, dao -> dao.countByCategoryIdAndFilters(categoryId, filters));
    }

    @Override
    public List<Product> getOrderedPartByCategoryIdAndFilters(int limit, int offset, String orderBy, String orderDir, long categoryId, String filters) {
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getOrderedPartByCategoryIdAndFilters(limit, offset, orderBy, orderDir, categoryId, filters));
    }

    @Override
    public int count() {
        return jdbi.withExtension(ProductDAO.class, ProductDAO::count);
    }

    @Override
    public void insertProductCategory(long productId, long categoryId) {
        jdbi.useExtension(ProductDAO.class, dao -> dao.insertProductCategory(productId, categoryId));
    }

    @Override
    public void updateProductCategory(long productId, long categoryId) {
        jdbi.useExtension(ProductDAO.class, dao -> dao.updateProductCategory(productId, categoryId));
    }

    @Override
    public void deleteProductCategory(long productId, long categoryId) {
        jdbi.useExtension(ProductDAO.class, dao -> dao.deleteProductCategory(productId, categoryId));
    }

    @Override
    public List<Product> getByQuery(String query, int limit, int offset) {
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getByQuery(query, limit, offset));
    }

    @Override
    public int countByQuery(String query) {
        return jdbi.withExtension(ProductDAO.class, dao -> dao.countByQuery(query));
    }

    public String getFirst(String twopartString) {
        return twopartString.contains("-") ? twopartString.split("-")[0] : "";
    }

    public String getLast(String twopartString) {
        return twopartString.contains("-") ? twopartString.split("-")[1] : "";
    }

    private int getMinPrice(String priceRange) {
        return Protector.of(() -> Integer.parseInt(getFirst(priceRange))).get(0);
    }

    private int getMaxPrice(String priceRange) {
        return Protector.of(() -> {
            String maxPriceString = getLast(priceRange);
            if (maxPriceString.equals("infinity")) {
                return Integer.MAX_VALUE;
            }
            return Integer.parseInt(maxPriceString);
        }).get(0);
    }

    public String filterByPublishers(List<String> publishers) {
        String publishersString = publishers.stream().map(p -> "'" + p + "'").collect(Collectors.joining(", "));
        return "p.publisher IN (" + publishersString + ")";
    }

    public String filterByPriceRanges(List<String> priceRanges) {
        String priceRangeConditions = priceRanges.stream().map(
                priceRange -> "p.price BETWEEN " + getMinPrice(priceRange) + " AND " + getMaxPrice(priceRange)
        ).collect(Collectors.joining(" OR "));
        return "(" + priceRangeConditions + ")";
    }

    public String createFiltersQuery(List<String> filters) {
        return String.join(" AND ", filters);
    }
}
