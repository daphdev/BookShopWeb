package com.bookshopweb;

import com.bookshopweb.service.ProductService;

import java.util.Arrays;
import java.util.Optional;

public class Test3 {
    public static void main(String[] args) {
        String priceRange1 = "0-20000";
        String priceRange2 = "20000-100000";
        String priceRange3 = "100000-infinity";


        ProductService productService = new ProductService();
        Optional<String> priceRangeParam = Optional.ofNullable(priceRange3);


        String s1 = productService.filterByPublishers(Arrays.asList("A", "B"));
        System.out.println(s1);

        String s2 = productService.filterByPriceRanges(Arrays.asList("0-50000", "50000-200000"));
        System.out.println(s2);
//        System.out.println(String.join(" AND ", Arrays.asList("A", "B")));
        String s = null;
        System.out.println(s.contains("-"));

    }
}
