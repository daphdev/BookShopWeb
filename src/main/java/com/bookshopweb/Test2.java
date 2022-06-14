package com.bookshopweb;

import com.bookshopweb.beans.Product;
import com.bookshopweb.beans.ProductReview;
import com.bookshopweb.service.ProductReviewService;
import com.bookshopweb.service.ProductService;

import java.util.Arrays;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        ProductReviewService productReviewService = new ProductReviewService();
        System.out.println("productReviewService.getOrderedPartByProductId()");
        List<ProductReview> pr = productReviewService.getOrderedPartByProductId(2, 0, "createdAt", "DESC", 54);
        pr.forEach(System.out::println);

        ProductService productService = new ProductService();

        List<String> filters = Arrays.asList(
//                productService.filterByPublishers(Arrays.asList("NXB Đại học Quốc gia Hà Nội", "NXB Đại học Sư phạm TP.HCM")),
                productService.filterByPriceRanges(Arrays.asList("0-50000", "50000-200000"))
        );
        String filtersString = String.join(" AND ", filters);
        List<Product> lp = productService.getOrderedPartByCategoryIdAndFilters(
                10, 0, "totalBuy", "DESC", 1, filtersString
        );
        lp.forEach(System.out::println);

        System.out.println(productService.countByCategoryIdAndFilters(1, filtersString));
    }
}
