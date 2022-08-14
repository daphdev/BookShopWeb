package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.Product;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.utils.Protector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    private static final int PRODUCTS_PER_PAGE = 12;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<String> query = Optional.ofNullable(request.getParameter("q")).filter(s -> !s.trim().isEmpty());

        if (query.isPresent()) {
            String queryStr = query.get();

            int totalProducts = Protector.of(() -> productService.countByQuery(queryStr)).get(0);
            int totalPages = totalProducts / PRODUCTS_PER_PAGE + (totalProducts % PRODUCTS_PER_PAGE != 0 ? 1 : 0);

            String pageParam = Optional.ofNullable(request.getParameter("page")).orElse("1");
            int page = Protector.of(() -> Integer.parseInt(pageParam)).get(1);
            if (page < 1 || page > totalPages) {
                page = 1;
            }

            int offset = (page - 1) * PRODUCTS_PER_PAGE;

            List<Product> products = Protector.of(() -> productService.getByQuery(
                    queryStr, PRODUCTS_PER_PAGE, offset
            )).get(ArrayList::new);

            products.forEach(product -> product.setName(product.getName()
                    .replaceAll("(?i)(" + queryStr + ")", "<b class='bg-warning'>$1</b>")));

            request.setAttribute("query", queryStr);
            request.setAttribute("totalProducts", totalProducts);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("page", page);
            request.setAttribute("products", products);
            request.getRequestDispatcher("/WEB-INF/views/searchView.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
